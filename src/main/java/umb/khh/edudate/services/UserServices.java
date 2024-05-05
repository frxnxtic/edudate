package umb.khh.edudate.services;

import jakarta.transaction.Transactional;
import umb.khh.edudate.entity.User;
import org.springframework.stereotype.Service;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.exception.DuplicateUsernameException;
import umb.khh.edudate.exception.InvalidUserDataException;
import umb.khh.edudate.exception.UserNotFoundException;
import umb.khh.edudate.repositories.UserRepository;
import java.util.List;
import java.util.Random;

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
      //user.getFaculty(userDTO.getFaculty());
        user.setInterest(userDTO.getInterest());
        user.setProfileDescription(userDTO.getProfileDescription());

        return userRepository.save(user);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUserName())) {
            throw new DuplicateUsernameException("Username " + user.getUserName() + " is already taken");
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


    public  User findRandomUser() {
        List<User> allUsers = userRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allUsers.size());
        return allUsers.get(randomIndex);
    }
}