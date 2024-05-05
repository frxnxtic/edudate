package umb.khh.edudate.services;

import umb.khh.edudate.entity.User;
import org.springframework.stereotype.Service;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.repositories.UserRepository;

import java.util.List;

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
        user.getFaculty(userDTO.getFaculty());
        user.setInterest(userDTO.getInterest());
        user.setProfileDescription(userDTO.getProfileDescription());


        return userRepository.save(user);
    }

    public User addUser(User user) {
        System.out.println(user.toString());
        return userRepository.save(user);

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }


}