package project.projectucvwebsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.entity.User;
import project.projectucvwebsystem.entity.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService { //Query by property //WARNING
    
    @Autowired
    private final UserRepository userRepository;

    //@Autowired
    /*public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    public Optional<User> obteinUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //@Query("SELECT COUNT(*) FROM USER_TABLE, nativeQuery=true")
    public int QuantityUsers () {
        return userRepository.countUsers();
    }

    public void InsertANewUser (
        String username,
        String password,
        String role
    ) {
        userRepository.InsertANewUser(username, password, role);
    }

}
