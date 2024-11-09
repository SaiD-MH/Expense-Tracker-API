package backend.expenses.controller;

import backend.expenses.entity.User;
import backend.expenses.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String createUser(@RequestBody User user){

        userService.createUser(user);

        return "Created";
    }

    @GetMapping("")
    List<User> getAllUsers(){

        return userService.getAllUsers();
    }





}
