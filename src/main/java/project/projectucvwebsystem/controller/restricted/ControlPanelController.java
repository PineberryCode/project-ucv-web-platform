package project.projectucvwebsystem.controller.restricted;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.projectucvwebsystem.Routes.Render;

@Controller
@RequestMapping("/restricted")
public class ControlPanelController {
    
    @GetMapping("/control-panel") //corregir /login-view/control-panel antes era /control-panel
    public String ControlPanel () {
        return Render.CONTROL_PANEL.name();
    }
    //accept-charset="utf-8"
}
