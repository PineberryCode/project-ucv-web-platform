package project.projectucvwebsystem.controller.restricted;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.routes.Render;
import project.projectucvwebsystem.service.EmployeeService;
import project.projectucvwebsystem.service.ProductService;
import project.projectucvwebsystem.service.SupplierService;
import project.projectucvwebsystem.service.UserService;

@Controller
@RequestMapping("/restricted")
public class ControlPanelController {

    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /*
     * Variables from DB:
     * Leyenda: 1Graph: Category, 2Graph: Name Large
     * Variable: Quantity
     */
    
    @GetMapping("/control-panel") //corregir /login-view/control-panel antes era /control-panel
    public String ControlPanel (Model model) {

        List<Object[]> listSupplierData = supplierService.findAllSuppliers();
        String supplierData = listSupplierData.stream()
        .map(supplier -> supplier[0]+"-"+supplier[1]+"-"+supplier[2]+"-"+supplier[3]+"-"+supplier[4])
        .collect(Collectors.joining(","));
        model.addAttribute("SupplierData", supplierData);

        String[] categories = productService.getCategories();
        String Categories = Arrays.stream(categories)
        .map(category -> ""+category+"").collect(Collectors.joining(","));
        model.addAttribute("Categories", Categories);

        List<Object[]> ObjectNameLargeAndQuantities = productService.getNameLargeAndQuantities();
        String joinedString = ObjectNameLargeAndQuantities.stream()
        .map(row -> row[0]+"-"+row[1])
        .collect(Collectors.joining(","));
        model.addAttribute("ObjectNameLargeAndQuantities", joinedString);
        //model.addAttribute("variable", userService.QuantityUsers());
        
        List<Object[]> listEmployeeData = employeeService.fillEmployeeRow();
        String joinedListEmployee = listEmployeeData.stream()
        .map(row -> row[0]+"-"+row[1]+"-"+row[2]+"-"+row[3]+"-"+row[4]+"-"+row[5])
        .collect(Collectors.joining(","));
        model.addAttribute("ListEmployeeTable", joinedListEmployee);

        return Render.CONTROL_PANEL.name();
    }

    @PostMapping("/control-panel/logout")
    public String Logout () {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/restricted");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return "redirect:/restricted/login-view";
    }

}
