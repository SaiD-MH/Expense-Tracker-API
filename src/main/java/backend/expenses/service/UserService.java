package backend.expenses.service;

import backend.expenses.entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    List<User> getAllUsers();
}
