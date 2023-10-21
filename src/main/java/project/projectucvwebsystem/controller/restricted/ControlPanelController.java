package project.projectucvwebsystem.controller.restricted;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.Routes.Render;
import project.projectucvwebsystem.service.ProductService;
import project.projectucvwebsystem.service.SupplierService;

@Controller
@RequestMapping("/restricted")
public class ControlPanelController {

    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;

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
        /*for (Object[] supplier : listSupplierData) {
            System.out.println(supplier[1]);
        }*/

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

    @PostMapping("/control-panel/delete-supplier")
    public String DeleteSupplier (
        @RequestParam("supplierID") int ID
    ) {

        supplierService.DeleteOnlySupplier(ID);

        return "redirect:/restricted/control-panel";
    }

}
