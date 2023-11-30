package project.projectucvwebsystem.dto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    private Long id_product;
    private String alias;
    private String name_large;
    private int quantity;
    private double unit_price;
    
    /*@Query (value = "SELECT p.ID_PRODUCT, c.ALIAS, p.NAME_LARGE, p.QUANTITY, p.UNIT_PRICE "+
    "FROM PRODUCT p "+
    "INNER JOIN CATEGORY c "+
    "ON c.ID_CATEGORY = p.ID_CATEGORY",
    nativeQuery = true)
    public List<ProductDTO[]> DataAllProducts ();*/
}