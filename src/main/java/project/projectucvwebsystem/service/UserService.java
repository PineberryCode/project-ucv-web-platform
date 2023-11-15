package project.projectucvwebsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.entity.User;
import project.projectucvwebsystem.entity.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService { //Query by property //WARNING
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    public Optional<User> obteinUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public int QuantityUsers () {
        return userRepository.countUsers();
    }

    public void InsertANewUser (
        String username,
        String password,
        String role
    ) {
        String passwordEncoded = passwordEncoder.encode(password);
        userRepository.InsertANewUser(username, passwordEncoded, role);
    }

    public int FindIDByUsername (String username ) {
        return userRepository.FindIDByUsernameString(username);
    }

    public int CatchIDEmployee (int ID) {
        return userRepository.BeforeRemoveUser(ID);
    }

    public void RemoveUser (int ID) {
        //int idUser = CatchIDEmployee(ID);
        userRepository.RemoveUser(ID);
    }

}
