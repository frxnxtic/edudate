package umb.khh.edudate.controllers;

import umb.khh.edudate.entity.User;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.services.UserServices;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserServices userService;

    public UserController(UserServices userService) {
        this.userService = userService;
    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

}
