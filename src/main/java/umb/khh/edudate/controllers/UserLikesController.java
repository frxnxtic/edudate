package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.UserLikes;
import umb.khh.edudate.services.UserLikesService;
import umb.khh.edudate.services.UserServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user-likes")
public class UserLikesController {

    @Autowired
    private final UserLikesService userLikesService;

    @Autowired
    private final UserServices userService;

    public UserLikesController(UserLikesService userLikesService, UserServices userService) {
        this.userLikesService = userLikesService;
        this.userService = userService;
    }

    @PostMapping("/like/{userId}")
    public ResponseEntity<Void> likeUser(@PathVariable Long userId) {
        UserDTO user = userService.getUserById1(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setLikes((short) (user.getLikes() + 1));
        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike/{userId}")
    public ResponseEntity<Void> DislikeUser(@PathVariable Long userId) {
        UserDTO user = userService.getUserById1(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setDislikes(user.getDislikes() + 1);
        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }
}
