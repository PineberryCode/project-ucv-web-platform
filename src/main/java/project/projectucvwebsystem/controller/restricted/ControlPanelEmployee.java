package project.projectucvwebsystem.controller.restricted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.service.EmployeeService;
import project.projectucvwebsystem.service.UserService;

@Controller
@RequestMapping("/restricted/control-panel")
public class ControlPanelEmployee {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;
    
    @PostMapping("/register-employee")
    public String RegisterEmployee (
        @RequestParam("email") String email,
        @RequestParam("name") String name,
        @RequestParam("lastname") String lastname,
        @RequestParam("address") String address,
        @RequestParam("username") String username,
        @RequestParam("roles") String role,
        @RequestParam("password") String password
    ) throws InterruptedException {

        userService.InsertANewUser(username, password, role);
        int idUser = userService.FindIDByUsername(username);
        Thread.sleep(500);
        
        employeeService.InsertNewEmployee(idUser, email, name, lastname, address);
        
        return "redirect:/restricted/control-panel";
    }

    @PostMapping("/update-employee")
    public String UpdateEmployee (
        @RequestParam("employeeID") int ID
    ) throws InterruptedException {

        Cookie[] cookies = request.getCookies();
        String extractedColumn = null;
        String extractedValue = null;

        for (Cookie cookie : cookies) {
            if ("extractedColumnEmployee".equals(cookie.getName())) {
                extractedColumn = cookie.getValue();
                break;
            }
        }

        for (Cookie cookie : cookies) {
            if ("extractedValueEmployee".equals(cookie.getName())) {
                extractedValue = cookie.getValue();

                extractedValue = extractedValue.contains("%20")
                ? extractedValue.replace("%20", " ")
                : extractedValue;

                extractedValue = extractedValue.contains("%40")
                ? extractedValue.replace("%40", "@")
                : extractedValue;

                break;
            }
        }

        switch (extractedColumn) {
            case "1" -> {
                int idUser = employeeService.CatchIDEmployee(ID);
                userService.UpdateUserRole(extractedValue, idUser);
            }
            case "2" -> {
                employeeService.UpdateEmployeeEmail(extractedValue, ID);
            }
            case "3" -> {
                employeeService.UpdateEmployeeNames(extractedValue, ID);
            }
            case "4" -> {
                employeeService.UpdateEmployeeLastname(extractedValue, ID);
            }
            case "5" -> {
                employeeService.UpdateEmployeeAddress(extractedValue, ID);
            }
        }

        return "redirect:/restricted/control-panel";
    }

    @PostMapping("/delete-employee")
    public String DeleteEmployee (
        @RequestParam("employeeID") int ID
    ) {

        int idEmployeeStored = employeeService.CatchIDEmployee(ID);

        employeeService.RemoveEmployee(ID);
        userService.RemoveUser(idEmployeeStored);
        
        return "redirect:/restricted/control-panel";
    }
}
