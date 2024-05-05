//package umb.khh.edudate.services;
//
//import org.apache.catalina.User;
//import org.springframework.stereotype.Service;
//import umb.khh.edudate.repositories.UserRepository;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User registerUser(User user) {
//        return null;
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//}
