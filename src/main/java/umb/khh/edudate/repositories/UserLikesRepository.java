package umb.khh.edudate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umb.khh.edudate.entity.UserLikes;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikesRepository extends JpaRepository<UserLikes, Long> {
    boolean existsByLikerUserIdAndLikedUserId(Long likerUserId, Long likedUserId);
    List<UserLikes> findByLikerUserId(Long likerUserId);
    List<UserLikes> findByLikedUserId(Long likedUserId);
    Optional<UserLikes> findByLikerUserIdAndLikedUserId(Long likerUserId, Long likedUserId);
}
