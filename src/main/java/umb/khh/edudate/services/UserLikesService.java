package umb.khh.edudate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umb.khh.edudate.entity.UserLikes;
import umb.khh.edudate.repositories.UserLikesRepository;
import umb.khh.edudate.repositories.UserRepository;

@Service
public class UserLikesService {

    @Autowired
    private UserLikesRepository userLikesRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveUserLike(Long likerUserId, Long likedUserId) {
        UserLikes userLikes = new UserLikes();
        userLikes.setLikerUserId(likerUserId);
        userLikes.setLikedUserId(likedUserId);
        userLikesRepository.save(userLikes);

        // Update like count
        var likedUser = userRepository.findById(likedUserId).orElse(null);
        if (likedUser != null) {
            likedUser.setLikes((short) (likedUser.getLikes() + 1));
            userRepository.save(likedUser);
        }

        // Check if mutual like exists
        if (userLikesRepository.existsByLikerUserIdAndLikedUserId(likedUserId, likerUserId)) {
            // Notify both users about the match
        }
    }

    public void saveUserDislike(Long likerUserId, Long dislikedUserId) {
        // Update dislike count
        var dislikedUser = userRepository.findById(dislikedUserId).orElse(null);
        if (dislikedUser != null) {
            dislikedUser.setDislikes(dislikedUser.getDislikes() + 1);
            userRepository.save(dislikedUser);
        }
    }
}
