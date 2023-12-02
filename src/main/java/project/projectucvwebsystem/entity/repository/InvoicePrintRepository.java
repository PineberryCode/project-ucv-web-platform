package project.projectucvwebsystem.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.projectucvwebsystem.entity.Sale;

public interface InvoicePrintRepository extends JpaRepository<Sale, Long> {
    
    @Query(
        value = "SELECT TOP 1 ID_SALES, CLIENT_SURNAME, CLIENT_CONTACT_NUMBER, IGV "+
        "FROM SALES "+
        "ORDER BY ID_SALES DESC ",
        nativeQuery = true
    )
    public String getGeneralForInvoice ();

    @Query(
        value = "SELECT p.NAME_LARGE, sd.REQUIRED_QUANTITY, p.UNIT_PRICE, "+
        "((s.IGV * p.UNIT_PRICE * sd.REQUIRED_QUANTITY)+(p.UNIT_PRICE * sd.REQUIRED_QUANTITY)) "+
        "FROM SALE_DETAILS sd "+
        "INNER JOIN PRODUCT p "+
        "ON sd.ID_PRODUCT = p.ID_PRODUCT "+
        "INNER JOIN SALES s "+
        "ON s.ID_SALES = sd.ID_SALES "+
        "WHERE sd.ID_SALES = :ID_SALES",
        nativeQuery = true
    )
    public List<Object[]> getProductInTable(
        @Param("ID_SALES") String idSales
    );
}
