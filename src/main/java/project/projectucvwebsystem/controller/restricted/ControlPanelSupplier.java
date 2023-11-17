package project.projectucvwebsystem.controller.restricted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.service.SupplierService;

@Controller
@RequestMapping("/restricted/control-panel")
public class ControlPanelSupplier {
    
    @Autowired
    SupplierService supplierService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @PostMapping("/register-supplier")
    public String RegisterSupplier (
        @RequestParam("name") String name,
        @RequestParam("cel") String cel,
        @RequestParam("email") String email,
        @RequestParam("address") String address
    ) {
        supplierService.CreateANewSupplier(name, cel, email, address);
        return "redirect:/restricted/control-panel";
    }

    @PostMapping("/update-supplier") //@PutMapping | @PatchMapping
    public String UpdateSupplier (
        @RequestParam("supplierID") int ID
    ) throws InterruptedException {

        Cookie[] cookies = request.getCookies();
        String extractedColumn = null;
        String extractedValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("extractedColumnSupplier".equals(cookie.getName())) {
                    extractedColumn = cookie.getValue();
                    break;
                }
            }
            for (Cookie cookie : cookies) {
                if ("extractedValueSupplier".equals(cookie.getName())) {
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
        }

        //System.out.println("IDDDD: "+ID);
        switch(extractedColumn) {
            case "1" -> { //Case = {num of column}
                supplierService.UpdateSupplierDescriptionName(extractedValue, ID);
            }
            case "2" -> {
                supplierService.UpdateSupplierNumber(extractedValue, ID);
            }
            case "3" -> {
                supplierService.UpdateSupplierEmail(extractedValue, ID);
            }
            case "4" -> {
                supplierService.UpdateSupplierAddress(extractedValue, ID);
            }
        }

        return "redirect:/restricted/control-panel";
    }

    @PostMapping("/delete-supplier") //@DeleteMapping
    public String DeleteSupplier (
        @RequestParam("supplierID") int ID
    ) {
        Cookie cookie = new Cookie("removed", "true");
        cookie.setMaxAge(60*60);
        cookie.setPath("/restricted");
        response.addCookie(cookie);

        supplierService.DeleteOnlySupplier(ID);

        return "redirect:/restricted/control-panel";
    }

}
