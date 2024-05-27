package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.Interest;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.exception.UserNotFoundException;
import umb.khh.edudate.services.UserServices;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserServices userService;
    public UserController(UserServices userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }//кнопка на главной странице(переносит нас на регистрационный формуляр)

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }//кнопка подтверждающая регистрацию пользователя

    @GetMapping("/user")
    public String getUserForRating(Model model) {
        User userForRating = userService.findRandomUser();
        model.addAttribute("user", userForRating);
        return "userRating";
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
        Long userId = userService.getUserIdByUsername(username);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User randomUser = userService.getUserById(id);
        if (randomUser != null) {
            return ResponseEntity.ok(randomUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    // Фиктивный метод для получения случайного пользователя
    @GetMapping("/myProfile")
    public User getMyProfile() {
        // Создание фиктивного пользователя
        User user = new User();
        user.setUsername("ahfeiu0");
        user.setName("Artem");
        user.setSurname("Haidosh");
        user.setEmail("artemhaydosh@gmail.com");
        Date dateOfBirthday = new Date(2005, Calendar.AUGUST, 28);
        user.setDateOfBirth(dateOfBirthday);
        user.setProfileDescription("Я курю, дрошу, матюгаюся...");
        user.setFaculty("FPV");
        user.setPassword("1234567890");

        Set<Interest> interests = new HashSet<>();
        interests.add(Interest.SPORT);
        interests.add(Interest.MUSIC);
        interests.add(Interest.TRAVEL);
        interests.add(Interest.FASHION);
        interests.add(Interest.READING);
        //interests.add(Interest.GAMING);

        user.setInterests(interests);

        userService.createUser(user);
        return user;
    }

    @GetMapping("/users/{id}/likes")
    public ResponseEntity<Set<User>> getUsersWhoLikedMe(@PathVariable Long id) {
        Set<User> likedByUsers = userService.getUsersWhoLikedMe(id);
        return ResponseEntity.ok(likedByUsers);
    }

    @PostMapping("/users/{id}/like")
    public ResponseEntity<User> likeUser(@PathVariable Long id, @RequestParam Long likedUserId) {
        User likedUser = userService.likeUser(id, likedUserId);
        return ResponseEntity.ok(likedUser);
    }


    @GetMapping("/{userId}/matches")
    public ResponseEntity<List<User>> getMatchingUsers(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<User> matchingUsers = userService.findUsersByCommonInterests(user);
        return ResponseEntity.ok(matchingUsers);
    }

}
