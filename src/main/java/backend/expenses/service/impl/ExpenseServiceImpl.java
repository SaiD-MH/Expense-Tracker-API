package backend.expenses.service.impl;

import backend.expenses.entity.Expense;
import backend.expenses.repository.ExpenseRepository;
import backend.expenses.repository.UserRepository;
import backend.expenses.service.ExpenseService;
import backend.expenses.service.validation.DbCheck;
import backend.expenses.service.validation.TimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static backend.expenses.service.validation.ParameterValidation.isValidParameter;

@Service
public class ExpenseServiceImpl implements ExpenseService {


    private final ExpenseRepository expenseRepository;
    private final DbCheck databaseChecker;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, DbCheck databaseChecker) {
        this.expenseRepository = expenseRepository;
        this.databaseChecker = databaseChecker;
    }

    @Override
    public void addExpense(Expense expense, int userId) {

        if (!databaseChecker.isUserExist(userId))
            throw new RuntimeException("User not exist");

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
}
