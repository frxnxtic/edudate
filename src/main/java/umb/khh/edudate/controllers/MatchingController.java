package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.services.MatchingService;
import umb.khh.edudate.services.UserServices;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/matching")
public class MatchingController {

    @Autowired
    private final MatchingService matchingService;

    @Autowired
    private final UserServices userService;

    public MatchingController(MatchingService matchingService, UserServices userService) {
        this.matchingService = matchingService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Matching> createMatching(@RequestParam Long userId, @RequestParam Long matchedUserId, @RequestParam boolean liked) {
        User user = userService.getUserById(userId);
        User matchedUser = userService.getUserById(matchedUserId);

        if (user == null || matchedUser == null) {
            return ResponseEntity.notFound().build();
        }

        Matching matching = new Matching();
        matching.setUser(user);
        matching.setMatchedUser(matchedUser);
        matching.setSeen(false); // Initially, the match is not seen
        matching.setLiked(liked);

        Matching savedMatching = matchingService.saveMatching(matching);
        return ResponseEntity.ok(savedMatching);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Matching>> getUserMatchings(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Matching> matchings = matchingService.getUserMatchings(user);
        return ResponseEntity.ok(matchings);
    }

    @GetMapping("/matched-by/{userId}")
    public ResponseEntity<List<Matching>> getUserMatchedBy(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Matching> matchedBy = matchingService.getUserMatchedBy(user);
        return ResponseEntity.ok(matchedBy);
    }
}
