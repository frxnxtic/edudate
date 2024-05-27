package umb.khh.edudate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umb.khh.edudate.entity.Matching;
import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findByUserId(Long userId);
    List<Matching> findByMatchedUserId(Long matchedUserId);
}
