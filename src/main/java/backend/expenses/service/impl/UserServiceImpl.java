package backend.expenses.service.impl;

import backend.expenses.entity.User;
import backend.expenses.repository.UserRepository;
import backend.expenses.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {

        userRepository.save(user);

    }
}
