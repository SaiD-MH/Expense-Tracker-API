package backend.expenses.service.impl;

import backend.expenses.entity.User;
import backend.expenses.repository.UserRepository;
import backend.expenses.service.UserService;
import backend.expenses.service.dbCounter.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CounterService counterService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, CounterService counterService) {
        this.userRepository = userRepository;
        this.counterService = counterService;
    }

    @Override
    public void createUser(User user) {

        int userId = counterService.getNextSequence("user");
        user.setId(userId);
        userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
