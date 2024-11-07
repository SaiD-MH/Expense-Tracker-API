package backend.expenses.repository;

import backend.expenses.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, Integer> {

    List<Expense> findByUserId(int id);

    @Query("{ 'userId': ?0, 'createdAt': { $gte: ?1, $lte: ?2 } }")
    List<Expense> findByCustomDate(int userId, LocalDate startDate, LocalDate endDate);


    Expense findByIdAndUserId(int id, int userId);

    Boolean existsByIdAndUserId(int expenseId, int userId);

    void deleteByIdAndUserId(int expenseId, int userId);

}
