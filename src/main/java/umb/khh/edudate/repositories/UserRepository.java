package umb.khh.edudate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import umb.khh.edudate.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    /*
    boolean existsByUsername(String name);
    */
    Optional<User> findByUsername(String username);

}
