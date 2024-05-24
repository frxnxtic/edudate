package umb.khh.edudate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findByUser(User user);
    List<Matching> findByMatchedUser(User matchedUser);
}
