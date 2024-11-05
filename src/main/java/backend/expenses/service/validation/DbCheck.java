package backend.expenses.service.validation;

import backend.expenses.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DbCheck {

    private UserRepository userRepository;

    public DbCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean isUserExist(int userId) {

        boolean found = userRepository.existsById(userId);
        System.out.println("INFO | USER | ID | " + userId + " | EXISTS | " + found);

        return found;
    }


}
