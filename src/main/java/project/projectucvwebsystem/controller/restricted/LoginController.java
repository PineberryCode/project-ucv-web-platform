package project.projectucvwebsystem.controller.restricted;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.projectucvwebsystem.Routes.Render;

@Controller
@RequestMapping("/restricted")
public class LoginController {
    
    @GetMapping("/login-view")
    public String LoginView() {
        return Render.LOGIN_VIEW.name();
    }
}
