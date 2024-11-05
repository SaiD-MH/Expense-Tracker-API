package backend.expenses.service;

import backend.expenses.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    void addExpense(Expense expense);

    List<Expense> getAllExpenses(int userId, String filterBy, Optional<Integer> days, Optional<Integer> months, String starDate, String endDate);
}
