package project.projectucvwebsystem.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import project.projectucvwebsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername (String username);
    
    @Query(value = "SELECT COUNT(*) FROM USER_TABLE", nativeQuery = true)
    int countUsers();

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO USER_TABLE (USERNAME, PASSWORD, USER_ROLE) "+
        "VALUES (:USERNAME, :PASSWORD, :USER_ROLE)",
        nativeQuery = true
    )
    public void InsertANewUser(
        @Param("USERNAME") String username,
        @Param("PASSWORD") String password,
        @Param("USER_ROLE") String role
    );

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM USER_TABLE "+
        "WHERE ID_USER = :ID",
        nativeQuery = true
    )
    public void RemoveUser (int ID);

    @Query(
        value = "SELECT ID_USER "+
        "FROM USER_TABLE "+
        "WHERE USERNAME = :USERNAME",
        nativeQuery = true
    )
    public int FindIDByUsernameString(@Param("USERNAME") String username);

    @Modifying
    @Transactional
    @Query (
        value = "UPDATE USER_TABLE "+
        "SET USER_ROLE = :USER_ROLE "+
        "WHERE ID_USER = :ID",
        nativeQuery = true
    )
    public void UpdateUserRole (
        @Param("USER_ROLE") String role,
        @Param("ID") int ID
    );
}
