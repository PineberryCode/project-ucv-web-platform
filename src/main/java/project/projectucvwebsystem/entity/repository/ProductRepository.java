package project.projectucvwebsystem.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.projectucvwebsystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query(value = "SELECT DISTINCT c.ALIAS FROM CATEGORY c JOIN PRODUCT p ON c.ID_CATEGORY = p.ID_CATEGORY", nativeQuery = true)
    public String[] Categories ();

    @Query(value = "SELECT NAME_LARGE, QUANTITY FROM PRODUCT", nativeQuery = true)
    public List<Object[]> NameLargeAndQuantity ();

}
