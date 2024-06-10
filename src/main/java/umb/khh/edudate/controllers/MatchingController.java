package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.enums.Interest;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.services.MatchingService;
import umb.khh.edudate.services.UserServices;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/matching")
public class MatchingController {

    private final MatchingService matchingService;
    private final UserServices userService;

    @Autowired
    public MatchingController(MatchingService matchingService, UserServices userService) {
        this.matchingService = matchingService;
        this.userService = userService;
    }
    @PostMapping("/{likerUserId}/like/{likedUserId}")
    public ResponseEntity<String> likeUser(@PathVariable Long likerUserId, @PathVariable Long likedUserId) {
        User likerUser = userService.getUserById(likerUserId);
        User likedUser = userService.getUserById(likedUserId);

        if (likerUser == null || likedUser == null) {
            return ResponseEntity.badRequest().body("Invalid user ID(s)");
        }

        boolean isMatch = matchingService.likeUser(likerUserId, likedUserId);
        if (isMatch) {
            return ResponseEntity.ok("It's a match!");
        } else {
            return ResponseEntity.ok("User liked successfully");
        }
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<List<User>> searchPartner(@PathVariable Long userId) {
        User currentUser = userService.getUserById(userId);
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }

        List<User> potentialMatches = matchingService.findPotentialMatches(currentUser);

        // Создаем Map для хранения userId и количества совпадающих интересов
        Map<User, Integer> userInterestCountMap = new HashMap<>();

        for (User matchedUser : potentialMatches) {
            int countInterest = calculateCommonInterests(currentUser, matchedUser);
            userInterestCountMap.put(matchedUser, countInterest);
        }

        // Сортируем Map по убыванию количества совпадающих интересов
        List<User> sortedUsers = userInterestCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<User, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(Arrays.toString(sortedUsers.toArray()));

        return ResponseEntity.ok(sortedUsers);
    }

    // Метод для подсчета количества общих интересов между двумя пользователями
    private int calculateCommonInterests(User user1, User user2) {
        List<Interest> interests1 = user1.getInterests();
        List<Interest> interests2 = user2.getInterests();
        interests1.retainAll(interests2);
        return interests1.size();
    }


}
