package umb.khh.edudate.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO convertToDTO(User userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
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

    public UserDTO updateUser(UserDTO userDTO) {
        System.out.println(userRepository.findByUsername(userDTO.getUsername()).stream().findFirst());
        User user = userRepository.findByUsername(userDTO.getUsername()).stream().findFirst().orElseThrow(() -> new UserNotFoundException("User not found with id: "  + userDTO.getUsername()));

        // Update user details if the new value is not null
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getSurname() != null) {
            user.setSurname(userDTO.getSurname());
        }
        if (userDTO.getDateOfBirth() != null) {
            user.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if (userDTO.getFaculty() != null) {
            user.setFaculty(userDTO.getFaculty());
        }
        if (userDTO.getProfileDescription() != null) {
            user.setProfileDescription(userDTO.getProfileDescription());
        }

        // Save updated user to the database
        User updatedUser = userRepository.save(user);

        // Convert updated user entity to DTO and return
        return userMapper.toUserDTO(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
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

    public UserDTO getUserById1(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.toUserDTO(user.orElse(null));
    }



    public ResponseEntity<UserDTO> login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.username()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.matches(CharBuffer.wrap(loginDTO.password()), user.getPassword())) {
            String token = userAuthProvider.createJWTToken(user);
            user.setToken(token);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + user.getToken());
            userRepository.save(user);
            System.out.println("User saved: " + user);
            System.out.println("User saved token: " + user.getToken());
            System.out.println("Headers: " + headers);
            System.out.println(userMapper.toUserDTO(user));
            System.out.println("ResponseEntity: " + new ResponseEntity<>(userMapper.toUserDTO(user), headers, HttpStatus.OK));
            return new ResponseEntity<>(userMapper.toUserDTO(user), headers, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public UserDTO register1(SignupDTO signUpDTO) {
        Optional<User> user = userRepository.findByUsername(signUpDTO.username());

        if (user.isPresent())  {
            throw new DuplicateUsernameException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User userEntity = new User();
        userEntity.setUsername(signUpDTO.username());
        userEntity.setEmail(signUpDTO.email());
        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));
        String token = userAuthProvider.createJWTToken(userEntity);
        userEntity.setToken(token);
        User updatedUser = userRepository.save(userEntity);


        return userMapper.toUserDTO(updatedUser);
    }

    public UserDTO register2(SignupDTO signUpDTO) {
        Optional<User> user = userRepository.findByUsername(signUpDTO.username());

        /*if (user.isPresent())  {
            throw new DuplicateUsernameException("Username already exists", HttpStatus.BAD_REQUEST);
        }*/

        System.out.println("User: " + user);
        //System.out.printf("Token" + user.getToken());

        User userEntity = new User();
        userEntity.setName(signUpDTO.name());
        userEntity.setSurname(signUpDTO.surname());
        userEntity.setDateOfBirth(signUpDTO.dateOfBirth());
        userEntity.setFaculty(signUpDTO.faculty());
        userEntity.setProfileDescription(signUpDTO.profileDescription());

        userRepository.save(userEntity);
        return userMapper.toUserDTO(userEntity);
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

    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        System.out.println("User: " + user.getId());
        return user.getId();
    }
}