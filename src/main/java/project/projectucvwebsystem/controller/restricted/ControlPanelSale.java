package project.projectucvwebsystem.controller.restricted;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import project.projectucvwebsystem.dto.InvoiceDTO;
import project.projectucvwebsystem.service.EmployeeService;
import project.projectucvwebsystem.service.InvoicePrintService;
import project.projectucvwebsystem.service.InvoiceService;
import project.projectucvwebsystem.service.JWTService;
import project.projectucvwebsystem.service.ProductService;
import project.projectucvwebsystem.service.SaleDetailsService;
import project.projectucvwebsystem.service.SaleService;
import project.projectucvwebsystem.service.UserService;

@Controller
@RequestMapping("/restricted/control-panel")
public class ControlPanelSale {
    
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SaleService saleService;

    @Autowired
    SaleDetailsService saleDetailsService;

    @Autowired
    InvoicePrintService invoicePrintService;

    @Autowired
    JWTService jwtService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    InvoiceService invoiceService;

    List<String[]> priceByProductListInCompilation = new ArrayList<>();
    double sum = 0.0;

    @PostMapping("/extract-category") 
    public void ExtractProduct () {
        Cookie[] cookies = request.getCookies();
        String category = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("selectedCategory".equals(cookie.getName())) {
                    category = cookie.getValue();
                    break;
                }
            }
        }

        List<String> listProducts = productService.getNameLargeByCategory(category);
        String productsString = listProducts.stream()
        .map(product -> ""+product+"").collect(Collectors.joining("%c"));

        productsString = productsString.contains(" ")
        ? productsString.replace(" ", "%20")
        : productsString;

        Cookie productsCookie = new Cookie("ProductsString", productsString);
        productsCookie.setMaxAge(60*60);
        productsCookie.setPath("/restricted");
        //productsCookie.setSecure(true);

        response.addCookie(productsCookie);
        System.out.println(productsString);
    }

    @PostMapping("/add-product")
    public ResponseEntity<byte[]> addProduct (
        @RequestParam("category") String category,
        @RequestParam("product-name") String productName,
        @RequestParam("quantity") int quantity
    ) throws Exception {

        invoiceService.setCategory(category);
        invoiceService.addProduct(productName, quantity);
        System.out.println(invoiceService.viewProducts());

        String[] priceByProductArr = saleService.showUniquePriceByProduct(quantity, productName);
        
        priceByProductListInCompilation.add(priceByProductArr);

        
        for (String[] strArr : priceByProductListInCompilation) {
            double aux =  Double.parseDouble(strArr[1]);
            sum += aux;
        }
        System.out.println("Sum: "+sum);

        StringJoiner joiner = new StringJoiner(",");
        DecimalFormat de = new DecimalFormat("#####.##");

        joiner.add(String.valueOf(priceByProductArr[0]));
        joiner.add(String.valueOf(de.format(sum)));

        byte[] priceAndIGV = joiner.toString().getBytes();

        Cookie cookieSaleDetails = new Cookie("CookieSaleDetails", invoiceService.viewProducts());
        //cookieSaleDetails.setMaxAge(60*60);
        cookieSaleDetails.setPath("/restricted");

        response.addCookie(cookieSaleDetails);
        
        return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(priceAndIGV);
        
    }

    @PostMapping("/delete-only-one-product")
    public void RemoveOnlyOneProduct (@RequestParam("key-product") String keyProduct) {
        System.out.println("Removed: "+keyProduct);
        invoiceService.removeProduct(keyProduct);

        Cookie cookieSaleDetails = new Cookie("CookieSaleDetails", invoiceService.viewProducts());
        //cookieSaleDetails.setMaxAge(60*60);
        cookieSaleDetails.setPath("/restricted");

        response.addCookie(cookieSaleDetails);
    }

    @PostMapping("/register-sale")
    public ResponseEntity<byte[]> RegisterSale (
        @RequestParam("surname") String surname,
        @RequestParam("cel-client") String celClient
    ) {

        /*
         * The process of looking for an employee id
         */
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        String username = jwtService.extractUsername(jwt);
        int idUser = userService.FindIDByUsername(username);

        int idEmployee = employeeService.RetrieveEmployeeIDByUserID(idUser);

        /*
         * End: 'Process to looking for employee id'
         * *****************************************
         */

        int loops = 0;

        String idSales = saleService.InsertSaleAndReturnID(idEmployee, surname, celClient);
        List<Object[]> list = invoiceService.OverviewProducts();

        System.out.println(invoiceService.Size());
        System.out.println(invoiceService.viewProducts());
        while (loops < invoiceService.Size() && invoiceService.Size() != 0) {
            Object[] entry = list.get(loops);
            String key = (String) entry[0];
            System.out.println("key: "+key);
            int quantity = invoiceService.obtainQuantity(key);
            Long idProduct = productService.getProducIDByName(key);

            saleDetailsService.InsertSaleDetails(idSales, idProduct, quantity);
            productService.RemoveStockUnits(quantity, idProduct);
            loops++;
        }

        try {
            InvoiceDTO headerData = invoicePrintService.getGeneralForInvoice();
            headerData.setTotal_all_products( ((Number) sum).doubleValue() );
            System.out.println("headerData: "+headerData.getId_sales());
            List<InvoiceDTO> exportedData = invoicePrintService.getDataForInvoiceTable(headerData.getId_sales());

            for (InvoiceDTO s : exportedData) {
                System.out.println("ex: "+s);
            }

            JasperPrint jasperPrint = generateJasperInvoicePrint(exportedData, headerData);

            byte[] pdfInvoicePrintArray = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "invoice-print.pdf");

            return new ResponseEntity<>(pdfInvoicePrintArray, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //return "redirect:/restricted/control-panel";
    }

    private JasperPrint generateJasperInvoicePrint(
        List<InvoiceDTO> exportedData, 
        InvoiceDTO headerData
    ) throws FileNotFoundException, JRException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("productInvoiceData", new JRBeanCollectionDataSource(exportedData));
        parameters.put("id_sales", headerData.getId_sales());
        parameters.put("surname", headerData.getSurname());
        parameters.put("client_cel", headerData.getClient_cel());
        parameters.put("igv", headerData.getIgv());
        parameters.put("total_all_products", headerData.getTotal_all_products());
        
        System.out.println("Mapita: "+headerData.getId_sales());

        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(
            ResourceUtils.getFile("classpath:report/print_invoice.jrxml").getAbsolutePath()
        ), parameters, new JREmptyDataSource());
        
        return jasperPrint;
    }

}
