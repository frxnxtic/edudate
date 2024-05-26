package umb.khh.edudate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.repositories.MatchingRepository;

import java.sql.Time;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    public Matching saveMatching(Matching matching) {
        matching.setMatchedAt(new Time(System.currentTimeMillis()));
        return matchingRepository.save(matching);
    }

    public List<Matching> getUserMatchings(User user) {
        return matchingRepository.findByUser(user);
    }

    public List<Matching> getUserMatchedBy(User matchedUser) {
        return matchingRepository.findByMatchedUser(matchedUser);
    }

    public Matching createMatching(User user, User matchedUser, boolean liked) {
        Matching matching = new Matching();
        matching.setUser(user);
        matching.setMatchedUser(matchedUser);
        matching.setSeen(false);
        matching.setLiked(liked);
        return saveMatching(matching);
    }
}
