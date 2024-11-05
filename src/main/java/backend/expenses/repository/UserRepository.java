package backend.expenses.repository;

import backend.expenses.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, String> {


    boolean existsById(int userId);

}
