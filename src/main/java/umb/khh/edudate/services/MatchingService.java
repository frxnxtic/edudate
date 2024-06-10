package umb.khh.edudate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umb.khh.edudate.entity.Matching;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.entity.UserLikes;
import umb.khh.edudate.repositories.MatchingRepository;
import umb.khh.edudate.repositories.UserLikesRepository;
import umb.khh.edudate.repositories.UserRepository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLikesRepository userLikesRepository;


    public boolean likeUser(Long likerUserId, Long likedUserId) {
        // Запись лайка в таблицу user_likes
        UserLikes userLike = new UserLikes();
        userLike.setLikerUserId(likerUserId);
        userLike.setLikedUserId(likedUserId);
        userLikesRepository.save(userLike);

        // Проверка наличия взаимного лайка
        Optional<UserLikes> reciprocalLike = userLikesRepository.findByLikerUserIdAndLikedUserId(likedUserId, likerUserId);

        if (reciprocalLike.isPresent()) {
            // Создание записи в таблице matching
            Matching matching1 = new Matching();
            matching1.setUserId(likerUserId);
            matching1.setMatchedUserId(likedUserId);
            matching1.setMatchedAt(Time.valueOf(LocalTime.now()));

            Matching matching2 = new Matching();
            matching2.setUserId(likedUserId);
            matching2.setMatchedUserId(likerUserId);
            matching2.setMatchedAt(Time.valueOf(LocalTime.now()));

            matchingRepository.save(matching1);
            matchingRepository.save(matching2);

            return true;
        }

        return false;
    }


    public List<User> findPotentialMatches(User user) {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(u -> !u.getId().equals(user.getId()))  // исключаем самого пользователя
                .collect(Collectors.toList());
    }
}
