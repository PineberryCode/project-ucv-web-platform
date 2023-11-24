package project.projectucvwebsystem.controller.restricted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import project.projectucvwebsystem.service.ProductService;

@Controller
@RequestMapping("/restricted/control-panel")
public class ControlPanelGraphic {
    
    @Autowired
    ProductService productService;

    @PostMapping("/request-category")
    public void SetUpGraphicStock (
        @RequestParam("button-category") String buttonCategory
    ) {
        //productService.getNameAndQuantityFromProductByCategory(buttonCategory);
        System.out.println("category: "+buttonCategory);
    }
}
