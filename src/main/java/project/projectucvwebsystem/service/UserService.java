package project.projectucvwebsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import project.projectucvwebsystem.entity.User;
import project.projectucvwebsystem.entity.repository.UserRepository;

//@Service
public class UserService { //Query by property
    
    private final UserRepository userRepository;

    //@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> obteinUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //@Query("SELECT COUNT(*) FROM USER_TABLE, nativeQuery=true")
    public int QuantityUsers () {
        return userRepository.countUsers();
    }

}
