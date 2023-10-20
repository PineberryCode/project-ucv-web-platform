package project.projectucvwebsystem.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.projectucvwebsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
    
    @Query(value = "SELECT COUNT(*) FROM USER_TABLE", nativeQuery = true)
    int countUsers();
}
