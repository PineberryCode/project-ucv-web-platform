package project.projectucvwebsystem.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleDTO {
    
    private String id_sales;
    private Long id_employee;
    private Date date_sale;
    private String name_large;
    private double total;
    
}
