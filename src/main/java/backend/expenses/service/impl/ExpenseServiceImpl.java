package backend.expenses.service.impl;

import backend.expenses.entity.Expense;
import backend.expenses.enums.Category;
import backend.expenses.repository.ExpenseRepository;
import backend.expenses.service.ExpenseService;
import backend.expenses.service.dbCounter.CounterService;
import backend.expenses.validation.DbCheck;
import backend.expenses.validation.TimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static backend.expenses.validation.ParameterValidation.isValidParameter;

@Service
public class ExpenseServiceImpl implements ExpenseService {


    private final ExpenseRepository expenseRepository;
    private final DbCheck databaseChecker;
    private final CounterService counterService;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, DbCheck databaseChecker, CounterService counterService) {
        this.expenseRepository = expenseRepository;
        this.databaseChecker = databaseChecker;
        this.counterService = counterService;


    }

    @Override
    public void addExpense(Expense expense, int userId) {

        if (!databaseChecker.validUser(userId, expense.getUserId()))
            throw new RuntimeException("User not exist");

        int expenseId = counterService.getNextSequence("expense");
        expense.setId(expenseId);
        expense.setCategory(expense.enumSettingCategory(expense.getCategory()));

        expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(int userId, String filterBy, Optional<Integer> days, Optional<Integer> months, String starDate, String endDate) {

        if (!databaseChecker.isUserExist(userId))
            throw new RuntimeException("User Not Found");


        List<Expense> resultSet = new ArrayList<>();

        if (filterBy.equalsIgnoreCase("days")) {

            if (!isValidParameter(days))
                throw new RuntimeException("Number of days must be positive");

            List<LocalDate> dateRange = TimeRange.calculateDayRanges(days.get());

            resultSet = expenseRepository.findByCustomDate(userId, dateRange.get(0), dateRange.get(1));

        } else if (filterBy.equalsIgnoreCase("months")) {

            if (!isValidParameter(months))
                throw new RuntimeException("Number of months must be positive");

            List<LocalDate> dateRange = TimeRange.calculateMonthRanges(months.get());
            resultSet = expenseRepository.findByCustomDate(userId, dateRange.get(0), dateRange.get(1));

        } else if (filterBy.equalsIgnoreCase("custom")) {

            List<LocalDate> dateRange = TimeRange.validateCustomRange(starDate, endDate);

            if (dateRange.isEmpty())
                throw new RuntimeException("Invalid date range");

            resultSet = expenseRepository.findByCustomDate(userId, dateRange.get(0), dateRange.get(1));

        } else
            throw new RuntimeException("Invalid Filter Type");

        return resultSet;
    }


    @Override
    public Expense updateExpense(Expense expense, int expenseId, int userId) {


        if (!databaseChecker.validUserAndExpense(userId, expense.getUserId(), expenseId))
            throw new RuntimeException("Invalid update operation , user not allowed to update this expense");


        Expense dbExpense = expenseRepository.findByIdAndUserId(expenseId, userId);
        dbExpense = expense;
        dbExpense.setCategory(dbExpense.enumSettingCategory(dbExpense.getCategory()));

        return expenseRepository.save(dbExpense);
    }


    @Override
    public void deleteExpense(int userId, int expenseId) {


        if (!databaseChecker.isUserExist(userId)) {
            throw new RuntimeException("User not exist");
        }
        if (!databaseChecker.isExpenseExist(expenseId, userId))
            throw new RuntimeException("No Expense with this id");


        expenseRepository.deleteByIdAndUserId(expenseId, userId);

    }
}
