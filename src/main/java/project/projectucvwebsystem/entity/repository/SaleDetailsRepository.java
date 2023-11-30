package project.projectucvwebsystem.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import project.projectucvwebsystem.entity.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SALE_DETAILS "+
    "(ID_SALES, ID_PRODUCT, REQUIRED_QUANTITY) "+
    "VALUES (:ID_SALES, :ID_PRODUCT, :REQUIRED_QUANTITY)",
    nativeQuery = true)
    public void InsertDetails (
        @Param("ID_SALES") String idSales,
        @Param("ID_PRODUCT") Long idProduct,
        @Param("REQUIRED_QUANTITY") int requiredQuantity
    );
}
