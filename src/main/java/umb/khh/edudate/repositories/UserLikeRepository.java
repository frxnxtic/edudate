package umb.khh.edudate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umb.khh.edudate.entity.UserLike;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
    boolean existsByLikerUserIdAndLikedUserId(Long likerUserId, Long likedUserId);
    List<UserLike> findByLikerUserId(Long likerUserId);
    List<UserLike> findByLikedUserId(Long likedUserId);
    Optional<UserLike> findByLikerUserIdAndLikedUserId(Long likerUserId, Long likedUserId);
}
