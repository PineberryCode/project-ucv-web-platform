package project.projectucvwebsystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import project.projectucvwebsystem.dto.AuthenticationResponse;
import project.projectucvwebsystem.entity.User;
import project.projectucvwebsystem.entity.repository.UserRepository;

@Service
public class AuthenticationService {
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JWTService jwtService;

    public AuthenticationResponse login (User user) {

        User sourceUser = userRepo.findByUsername(user.getUsername()).get();
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken (
            user.getUsername(), user.getPassword()  
        );

        authManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authToken);
        String jwt = jwtService.GenerateToken(sourceUser, GenerateExtraClaims(sourceUser));
        
        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> GenerateExtraClaims (User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("user_role", user.getUser_role().name());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims;
    }
}
