package project.projectucvwebsystem.controller.restricted;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.projectucvwebsystem.service.ProductService;

@Controller
@RequestMapping("/restricted/control-panel")
public class ControlPanelGraphic {
    
    @Autowired
    ProductService productService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @PostMapping("/request-category")
    public void SetUpGraphicStock (
        @RequestParam("button-category") String buttonCategory
    ) {
        //productService.getNameAndQuantityFromProductByCategory(buttonCategory);
        List<Object[]> productsList = productService.getNameAndQuantityFromProductByCategory(buttonCategory);
        String productJoined = productsList.stream()
        .map(product -> product[0]+"%80"+product[1]).collect(Collectors.joining("%0"));

        //System.out.println(productJoined);

        productJoined = productJoined.contains(" ")
        ? productJoined.replace(" ", "%20")
        : productJoined;

        Cookie productByCategoryCookie = new Cookie("ButtonCategory", productJoined);
        productByCategoryCookie.setMaxAge(60*60);
        productByCategoryCookie.setPath("/restricted");

        System.out.println(productJoined);

        response.addCookie(productByCategoryCookie);
        System.out.println("category: "+buttonCategory);
    }
}
