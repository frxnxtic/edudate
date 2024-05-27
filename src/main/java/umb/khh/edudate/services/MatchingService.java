package umb.khh.edudate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.repositories.MatchingRepository;
import umb.khh.edudate.repositories.UserRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private UserRepository userRepository;

    public Matching createMatching(Long userId, Long matchedUserId, boolean liked) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User matchedUser = userRepository.findById(matchedUserId).orElseThrow(() -> new IllegalArgumentException("Matched user not found"));

        Matching matching = new Matching();
        matching.setUser(user);
        matching.setMatchedUserId(matchedUserId);  // оставляем как есть, так как matchedUserId все еще Long
        matching.setSeen(false);
        matching.setLiked(liked);
        matching.setMatchedAt(new Time(new Date().getTime()));
        return matchingRepository.save(matching);
    }

    public List<Matching> findMatchingsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return matchingRepository.findByUserId(user.getId());
    }

    public List<Matching> getUserMatchings(User user) {
        return matchingRepository.findByUserId(user.getId());
    }

    public List<Matching> getUserMatchedBy(User user) {
        return matchingRepository.findByMatchedUserId(user.getId());
    }

    public List<User> findPotentialMatches(User user) {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(u -> !u.getId().equals(user.getId()))  // исключаем самого пользователя
                .collect(Collectors.toList());
    }
}
