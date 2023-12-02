package project.projectucvwebsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDTO {
    
    private String id_sales;
    private String surname;
    private String client_cel;
    private double igv;
    private double total_all_products;

    private String name_large_product;
    private int quantity;
    private double unit_price;
    private double total;

}
