package project.projectucvwebsystem.controller.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.projectucvwebsystem.routes.Render;

@Controller
@RequestMapping("/global")
public class WelcomeController {

    @GetMapping("/welcome")
    public String Welcome () {
        return Render.WELCOME.name();
    }
}
