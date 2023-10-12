package project.projectucvwebsystem.controller.restricted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.Routes.Render;
import project.projectucvwebsystem.dto.AuthenticationResponse;
import project.projectucvwebsystem.entity.User;
import project.projectucvwebsystem.service.AuthenticationService;

@Controller
@RequestMapping("/restricted")
public class LoginController {
    
    @Autowired
    private AuthenticationService authService;

    @GetMapping("/login-view")
    public String LoginView () {
        return Render.LOGIN_VIEW.name();
    }

    @PostMapping("/login-view/login")
    public String Login (
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        HttpServletResponse response
    ) throws IOException, InterruptedException {
        
        AuthenticationResponse jwt = authService.login(new User(username, password));

        Cookie cookie = new Cookie("token", jwt.getJWT());
        cookie.setMaxAge(60*60);
        cookie.setPath("/restricted"); //Could be 'restricted/**'
        response.addCookie(cookie);
        
        return "redirect:/restricted/control-panel";
    }
}
