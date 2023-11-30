package project.projectucvwebsystem.controller.restricted;

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
public class ControlPanelProduct {
    
    @Autowired
    ProductService productService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;
    
    @PostMapping("/register-product")
    public String RegisterProduct (
        @RequestParam("category") int idCategory,
        @RequestParam("name") String nameProduct,
        @RequestParam("quantity") int quantity,
        @RequestParam("unit-price") double unitPrice
    ) {

        productService.InsertNewProduct(
            idCategory, 
            nameProduct, 
            quantity, 
            unitPrice
        );

        System.out.println("Category: "+idCategory);

        return "redirect:/restricted/control-panel";
    }

    @PostMapping("/update-product")
    public String UpdateProduct (
        @RequestParam("productID") Long idProduct
    ) {
        
        /*
         * Request to the cookie
         */
        Cookie[] cookies = request.getCookies();
        String extractedColumn = null;
        String extractedValue = null;

        if (cookies != null) {
            
            for (Cookie cookie : cookies) {
                if ("extractedColumnProductOriginal".equals(cookie.getName())) {
                    extractedColumn = cookie.getValue();
                    break;
                }
            }

            for (Cookie cookie : cookies) {
                if ("extractedValueProductOriginal".equals(cookie.getName())) {
                    extractedValue = cookie.getValue();

                    extractedValue = extractedValue.contains("%20") 
                    ? extractedValue.replace("%20", " ") 
                    : extractedValue;

                    break;
                }
            }
        }

        switch (extractedColumn) {
            
            case "1" -> {
                productService.UpdateCategoryFromProduct(
                extractedValue, idProduct);
            }
            case "2" -> {productService.UpdateNameLargeFromProduct(
                extractedValue, idProduct);
            }
            case "3" -> {productService.UpdateQuantityFromProduct(
                Integer.parseInt(extractedValue), idProduct);
            }
            case "4" -> {productService.UpdateUnitPriceFromProduct(
                Double.parseDouble(extractedValue), idProduct);
            }
            
        }
        
        return "redirect:/restricted/control-panel";
    }
}
