package umb.khh.edudate.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import umb.khh.edudate.dto.LoginDTO;
import umb.khh.edudate.dto.SignupDTO;
import umb.khh.edudate.entity.Interest;
import umb.khh.edudate.entity.User;
import org.springframework.stereotype.Service;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.exception.DuplicateUsernameException;
import umb.khh.edudate.exception.UserNotFoundException;
import umb.khh.edudate.repositories.UserRepository;
import umb.khh.edudate.security.AuthProvider;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Autowired
    private AuthProvider userAuthProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFaculty(userDTO.getFaculty());
        //user.setInterest(userDTO.getInterest());
        user.setProfileDescription(userDTO.getProfileDescription());

        return userRepository.save(user);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException("Username " + user.getUsername() + " is already taken", HttpStatus.BAD_REQUEST);
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

        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFaculty(user.getFaculty());
        //user.setInterest(userDTO.getInterest());
        newUser.setProfileDescription(user.getProfileDescription());

        return userRepository.save(newUser);

    }

    public UserDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.matches(CharBuffer.wrap(loginDTO.password()), user.getPassword())) {
            String token = userAuthProvider.createJWTToken(user);
            user.setToken(token);
            User saved = userRepository.save(user);
            return userMapper.toUserDTO(saved);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public UserDTO register(SignupDTO signUpDTO) {
        Optional<User> user = userRepository.findByUsername(signUpDTO.username());

        if (user.isPresent())  {
            throw new DuplicateUsernameException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User userEntity = userMapper.signUpToUser(signUpDTO);
        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));

        User savedUser = userRepository.save(userEntity);
        return userMapper.toUserDTO(savedUser);
    }

    public List<User> findUsersByCommonInterests(User user) {
        Set<Interest> userInterests = user.getInterests();
        return userRepository.findAll().stream()
                .filter(otherUser -> !otherUser.getId().equals(user.getId()))
                .sorted((u1, u2) -> {
                    long commonInterests1 = u1.getInterests().stream().filter(userInterests::contains).count();
                    long commonInterests2 = u2.getInterests().stream().filter(userInterests::contains).count();
                    return Long.compare(commonInterests2, commonInterests1);
                })
                .collect(Collectors.toList());
    }
}