package umb.khh.edudate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.repositories.MatchingRepository;
import umb.khh.edudate.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private UserRepository userRepository;

    public Matching saveMatching(Matching matching) {
        matching.setMatchedAt(new Date());
        return matchingRepository.save(matching);
    }

    public List<Matching> getUserMatchings(User user) {
        return matchingRepository.findByUser(user);
    }

    public List<Matching> getUserMatchedBy(User matchedUser) {
        return matchingRepository.findByMatchedUser(matchedUser);
    }
}
