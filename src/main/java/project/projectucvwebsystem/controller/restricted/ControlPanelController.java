package project.projectucvwebsystem.controller.restricted;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.Routes.Render;
import project.projectucvwebsystem.service.GraphDataService;
import project.projectucvwebsystem.service.ProductService;

@Controller
@RequestMapping("/restricted")
public class ControlPanelController {

    @Autowired
    ProductService productService;

    @Autowired
    GraphDataService graphDataService;

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
