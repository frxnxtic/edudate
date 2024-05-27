package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.entity.Interest;
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

    @Autowired
    private final MatchingService matchingService;

    @Autowired
    private final UserServices userService;

    public MatchingController(MatchingService matchingService, UserServices userService) {
        this.matchingService = matchingService;
        this.userService = userService;
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<List<User>> searchMatching(@PathVariable Long userId) {
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

        return ResponseEntity.ok(sortedUsers);
    }

    // Метод для подсчета количества общих интересов между двумя пользователями
    private int calculateCommonInterests(User user1, User user2) {
        int countInterest = 0;
        Set<Interest> interests1 = user1.getInterests();
        Set<Interest> interests2 = user2.getInterests();
        for (Interest interest1 : interests1) {
            for (Interest interest2 : interests2) {
                if (interest1 == interest2) {
                    countInterest++;
                    break;
                }
            }
        }
        return countInterest;
    }

    @PostMapping("/create")
    public ResponseEntity<Matching> createMatching(@RequestParam Long userId, @RequestParam Long matchedUserId, @RequestParam boolean liked) {
        User user = userService.getUserById(userId);
        User matchedUser = userService.getUserById(matchedUserId);

        if (user == null || matchedUser == null) {
            return ResponseEntity.notFound().build();
        }

        Matching matching = matchingService.createMatching(userId, matchedUserId, liked);
        return ResponseEntity.ok(matching);
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
