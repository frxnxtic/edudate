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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.entity.enums.Interest;
import umb.khh.edudate.exception.LikeAlreadyExistsException;
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

    @GetMapping("/user/{username}")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
        Long userId = userService.getUserIdByUsername(username);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @GetMapping("/user")
    public String getUserForRating(Model model) {
        User userForRating = userService.findRandomUser();
        model.addAttribute("user", userForRating);
        return "userRating";
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



//    @GetMapping("/{userId}/matches")
//    public ResponseEntity<List<User>> getMatchingUsers(@PathVariable Long userId) {
//        User user = userService.getUserById(userId);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        List<User> matchingUsers = userService.findUsersByCommonInterests(user);
//        return ResponseEntity.ok(matchingUsers);
//    }


    @PostMapping("/{likedUserId}/like")
    public ResponseEntity<Void> likeUser(@RequestParam Long likerUserId, @PathVariable Long likedUserId) {
        try {
            userService.likeUser(likerUserId, likedUserId);
            return ResponseEntity.ok().build();
        } catch (LikeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{userId}/likes")
    public ResponseEntity<List<User>> getUserLikes(@PathVariable Long userId) {
        try {
            List<User> likedUsers = userService.getUserLikes(userId);
            return ResponseEntity.ok(likedUsers);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{userId}/likesReceived")
    public ResponseEntity<List<User>> getUsersWhoLikedMe(@PathVariable Long userId) {
        try {
            List<User> usersWhoLikedMe = userService.getUsersWhoLikedMe(userId);
            return ResponseEntity.ok(usersWhoLikedMe);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/{userId}/interests")
    public ResponseEntity<Void> updateInterests(@PathVariable Long userId, @RequestBody List<Interest> interests) {
        try {
            userService.updateInterests(userId, interests);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{userId}/interests")
    public ResponseEntity<List<Interest>> getInterests(@PathVariable Long userId) {
        try {
            List<Interest> interests = userService.getInterests(userId);
            return ResponseEntity.ok(interests);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{userId}/matching-users")
    public ResponseEntity<List<User>> findUsersByCommonInterests(@PathVariable Long userId) {
        try {
            List<User> matchingUsers = userService.findUsersByCommonInterests(userId);
            return ResponseEntity.ok(matchingUsers);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
