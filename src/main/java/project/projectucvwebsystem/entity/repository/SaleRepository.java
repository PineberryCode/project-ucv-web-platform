package project.projectucvwebsystem.entity.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import project.projectucvwebsystem.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, String> {
    
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
