package project.projectucvwebsystem.entity.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import project.projectucvwebsystem.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, String> {
    
    @Query(
        value = "SELECT TOP 1 s.IGV, "+
        "(SELECT ((UNIT_PRICE * :REQUIRED_QUANTITY * s.IGV)+(UNIT_PRICE * :REQUIRED_QUANTITY)) "+
        "FROM PRODUCT "+
        "WHERE NAME_LARGE = :NAME_LARGE) "+
        "FROM SALES s",
        nativeQuery = true
    )
    public String showUniquePriceByProduct(
        @Param("REQUIRED_QUANTITY") int requiredQuantity,
        @Param("NAME_LARGE") String nameLarge
    );

    @Query(
        value = "SELECT s.ID_SALES, s.ID_EMPLOYEE, s.DATE_SALE, p.NAME_LARGE, "+
        "((p.UNIT_PRICE * sd.REQUIRED_QUANTITY * s.IGV)+(p.UNIT_PRICE * sd.REQUIRED_QUANTITY)) AS TOTAL "+
        "FROM SALES s "+
        "INNER JOIN SALE_DETAILS sd "+
        "ON s.ID_SALES = sd.ID_SALES "+
        "INNER JOIN PRODUCT p "+
        "ON sd.ID_PRODUCT = p.ID_PRODUCT",
        nativeQuery = true
    )
    public List<Object[]> saleReport();

    @Query(
        value = "SELECT COUNT(ID_SALES) "+
        "FROM SALES",
        nativeQuery = true
    )
    public int CountRows();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SALES "+
    "(ID_SALES, ID_EMPLOYEE, DATE_SALE, CLIENT_SURNAME, CLIENT_CONTACT_NUMBER, IGV) "+
    "VALUES (:ID_SALES, :ID_EMPLOYEE, :DATE_SALE, :CLIENT_SURNAME, :CLIENT_CONTACT_NUMBER, :IGV)",
    nativeQuery = true)
    public void InsertSale (
        @Param("ID_SALES") String idSales,
        @Param("ID_EMPLOYEE") int idEmployee,
        @Param("DATE_SALE") Date currentDate, //date.sql
        @Param("CLIENT_SURNAME") String clientSurname,
        @Param("CLIENT_CONTACT_NUMBER") String clientPhone,
        @Param("IGV") double igv
    );
}
