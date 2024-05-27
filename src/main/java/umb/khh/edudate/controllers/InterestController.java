package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.Interest;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.services.UserServices;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/interests")
public class InterestController {

    @Autowired
    private final UserServices userService;

    public InterestController(UserServices userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Set<Interest>> getUserInterests(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getInterests());
    }

    @PostMapping("/set/{userId}")
    public ResponseEntity<String> setUserInterests(@PathVariable Long userId, @RequestBody Set<Interest> interests) {
        UserDTO user = userService.getUserById1(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setInterests(interests);
        userService.updateUser(user);
        return ResponseEntity.ok("Interests set successfully for user with id: " + userId);
    }
}
