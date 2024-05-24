package umb.khh.edudate.services;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import umb.khh.edudate.entity.User;
import org.springframework.stereotype.Service;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.exception.DuplicateUsernameException;
import umb.khh.edudate.exception.UserNotFoundException;
import umb.khh.edudate.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setFaculty(userDTO.getFaculty());
      //user.setInterest(userDTO.getInterest());
        user.setProfileDescription(userDTO.getProfileDescription());

        return userRepository.save(user);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException("Username " + user.getUsername() + " is already taken");
        }

        return userRepository.save(user);
    }

    // Метод для удаления пользователя по ID
    @Transactional
    public void deleteUser(Long id) throws UserNotFoundException {
        // Проверяем, существует ли пользователь с указанным ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        // Если пользователь найден, удаляем его
        userRepository.delete(user);
    }


    public User findRandomUser() {
        List<User> allUsers = userRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allUsers.size());
        return allUsers.get(randomIndex);
    }

    public Set<User> getUsersWhoLikedMe(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getLikedBy();
    }

    public User likeUser(Long likerId, Long likedUserId) {
        User liker = userRepository.findById(likerId).orElseThrow(() -> new UserNotFoundException("Liker not found"));
        User likedUser = userRepository.findById(likedUserId).orElseThrow(() -> new UserNotFoundException("Liked user not found"));

        likedUser.getLikedBy().add(liker);
        userRepository.save(likedUser);

        return likedUser;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User updateUser(@RequestBody User user) {
        User newUser = new User();

        user.setName(user.getName());
        user.setSurname(user.getSurname());
        user.setDateOfBirth(user.getDateOfBirth());
        user.setEmail(user.getEmail());
        user.setPasswordHash(user.getPasswordHash());
        user.setFaculty(user.getFaculty());
        //user.setInterest(userDTO.getInterest());
        user.setProfileDescription(user.getProfileDescription());

        return userRepository.save(user);

    }
}