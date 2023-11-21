package project.projectucvwebsystem.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import project.projectucvwebsystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query(
        value = "SELECT DISTINCT c.ALIAS "+
        "FROM CATEGORY c JOIN PRODUCT p "+
        "ON c.ID_CATEGORY = p.ID_CATEGORY",
        nativeQuery = true)
    public String[] Categories ();

    @Query(value = "SELECT NAME_LARGE, QUANTITY "+
    "FROM PRODUCT",
    nativeQuery = true)
    public List<Object[]> NameLargeAndQuantity ();

    @Query (value = "SELECT p.ID_PRODUCT, c.ALIAS, p.NAME_LARGE, p.QUANTITY, p.UNIT_PRICE "+
    "FROM PRODUCT p "+
    "INNER JOIN CATEGORY c "+
    "ON c.ID_CATEGORY = p.ID_CATEGORY",
    nativeQuery = true)
    public List<Object[]> DataAllProducts ();

    @Query(
        value = "SELECT p.NAME_LARGE "+
        "FROM PRODUCT p "+
        "INNER JOIN CATEGORY c "+
        "ON p.ID_CATEGORY = c.ID_CATEGORY "+
        "WHERE c.ALIAS = :ALIAS",
        nativeQuery = true
    )
    public List<String> NameLargeByCategory (
        @Param("ALIAS") String alias
    );

    @Modifying
    @Transactional
    @Query (
        value = "INSERT INTO PRODUCT "+
        "VALUES (:ID_CATEGORY, :NAME_LARGE, :QUANTITY, :UNIT_PRICE)",
        nativeQuery = true
    )
    public void InsertNewProduct (
        @Param("ID_CATEGORY") int category,
        @Param("NAME_LARGE") String nameLarge,
        @Param("QUANTITY") int quantity,
        @Param("UNIT_PRICE") double unitPrice
    );

    @Modifying
    @Transactional
    @Query (
        value = "UPDATE PRODUCT "+
        "SET ID_CATEGORY = "+
        "CASE "+
            "WHEN :CATEGORY = 'Porcelanato' THEN 1 "+
            "WHEN :CATEGORY = 'Inodoro' THEN 2 "+
            "WHEN :CATEGORY = 'Lavadero' THEN 3 "+
            "WHEN :CATEGORY = 'Accesorios' THEN 4 "+
        "END "+
        "WHERE ID_PRODUCT = :ID",
        nativeQuery = true
    )
    public void UpdateCategoryFromProduct (
        @Param("CATEGORY") String category,
        @Param("ID") int idProduct
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE PRODUCT SET NAME_LARGE = :NAME_LARGE "+
        "WHERE ID_PRODUCT = :ID",
        nativeQuery = true
    )
    public void UpdateNameLargeFromProduct (
        @Param("NAME_LARGE") String nameLarge,
        @Param("ID") int idProduct
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE PRODUCT SET QUANTITY = :QUANTITY "+
        "WHERE ID_PRODUCT = :ID",
        nativeQuery = true
    )
    public void UpdateQuantityFromProduct (
        @Param("QUANTITY") int quantity,
        @Param("ID") int idProduct
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE PRODUCT SET UNIT_PRICE = :UNIT_PRICE "+
        "WHERE ID_PRODUCT = :ID",
        nativeQuery = true
    )
    public void UpdateUnitPriceFromProduct (
        @Param("UNIT_PRICE") double unitPrice,
        @Param("ID") int idProduct
    );

    @Modifying
    @Transactional
    @Query (
        value = "DELETE PRODUCT WHERE ID_PRODUCT = :ID",
        nativeQuery = true
    )
    public void RemoveProduct (
        @Param("ID") int id
    );

}
