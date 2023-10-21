package project.projectucvwebsystem.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.projectucvwebsystem.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    
    @Query(value = "SELECT * FROM SUPPLIER", nativeQuery = true)
    public List<Object[]> findAllSuppliers();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SUPPLIER WHERE ID_SUPPLIER = :ID", nativeQuery = true)
    public void DeleteOnlySupplier (@Param("ID") int ID);

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO SUPPLIER "+
        "VALUES (:DESCRIPTION_NAME,:CONTACT_NUMBER,:EMAIL,:ADDRESS)", 
        nativeQuery = true
    )
    public void CreateANewSupplier (
        @Param("DESCRIPTION_NAME") String name,
        @Param("CONTACT_NUMBER") String number,
        @Param("EMAIL") String email,
        @Param("ADDRESS") String address
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE SUPPLIER "+
        "SET DESCRIPTION_NAME = :DESCRIPTION_NAME "+
        "WHERE ID_SUPPLIER = :ID", 
        nativeQuery = true
    )
    public void UpdateSupplierDescriptionName (
        @Param("DESCRIPTION_NAME") String name,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE SUPPLIER "+
        "SET CONTACT_NUMBER = :CONTACT_NUMBER "+
        "WHERE ID_SUPPLIER = :ID",
        nativeQuery = true
    )
    public void UpdateSupplierContactNumber (
        @Param("CONTACT_NUMBER") String number,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE SUPPLIER "+
        "SET EMAIL = :EMAIL "+
        "WHERE ID_SUPPLIER = :ID",
        nativeQuery = true
    )
    public void UpdateSupplierEmail (
        @Param("EMAIL") String email,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE SUPPLIER "+
        "SET ADDRESS = :ADDRESS "+
        "WHERE ID_SUPPLIER = :ID",
        nativeQuery = true
    )
    public void UpdateSupplierAddress (
        @Param("ADDRESS") String address,
        @Param("ID") int ID
    );
}
