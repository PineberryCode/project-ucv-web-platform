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
}
