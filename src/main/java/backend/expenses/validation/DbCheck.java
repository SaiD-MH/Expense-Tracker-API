package backend.expenses.validation;

import backend.expenses.repository.ExpenseRepository;
import backend.expenses.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DbCheck {

    private UserRepository userRepository;
    private ExpenseRepository expenseRepository;


    public DbCheck(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public boolean isUserExist(int userId) {
        return userRepository.existsById(userId);
    }

    private boolean sameUser(int userId, int internalUserId) {
        return userId == internalUserId;
    }

    public boolean isExpenseExist(int expenseId, int userId) {

        return expenseRepository.existsByIdAndUserId(expenseId, userId);
    }


    public boolean validUser(int userId, int internalUserId) {

        return isUserExist(userId) && sameUser(userId, internalUserId);

    }


    public boolean validUserAndExpense(int userId, int internalUserId, int expenseId) {
        return validUser(userId, internalUserId) && isExpenseExist(expenseId, userId);
    }


}
