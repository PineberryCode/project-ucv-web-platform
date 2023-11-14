package project.projectucvwebsystem.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import project.projectucvwebsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
    @Query(
    value = "SELECT e.ID_EMPLOYEE, u.USER_ROLE, e.EMAIL, e.NAMES, e.LASTNAME, e.ADDRESS "+ 
    "FROM EMPLOYEE e "+
    "INNER JOIN USER_TABLE u "+
    "ON e.ID_USER = u.ID_USER", nativeQuery = true)
    public List<Object[]> fillEmployeeRow ();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM EMPLOYEE WHERE ID_EMPLOYEE = :ID", nativeQuery = true)
    public void DeleteEmployee (@Param("ID") int ID);

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO EMPLOYEE "+
        "VALUES (:ID_USER, :EMAIL, :NAMES, :LASTNAME, :ADDRESS)",
        nativeQuery = true
    )
    public void CreateANewEmployee (
        @Param("ID_USER") int idUser,
        @Param("EMAIL") String email,
        @Param("NAMES") String names,
        @Param("LASTNAME") String lastname,
        @Param("ADDRESS") String address
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE EMPLOYEE "+
        "SET USER_ROLE = :USER_ROLE "+
        "WHERE ID_EMPLOYEE = :ID",
        nativeQuery = true
    )
    public void UpdateEmployeeRole (
        @Param("USER_ROLE") String role,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE EMPLOYEE "+
        "SET EMAIL = :EMAIL "+
        "WHERE ID_EMPLOYEE = :ID",
        nativeQuery = true
    )
    public void UpdateEmployeeEmail (
        @Param("EMAIL") String email,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE EMPLOYEE "+
        "SET NAMES = :NAMES "+
        "WHERE ID_EMPLOYEE = :ID",
        nativeQuery = true
    )
    public void UpdateEmployeeNames (
        @Param("NAMES") String names,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE EMPLOYEE "+
        "SET LASTNAME = :LASTNAME "+
        "WHERE ID_EMPLOYEE = :ID",
        nativeQuery = true
    )
    public void UpdateEmployeeLastname (
        @Param("LASTNAME") String lastname,
        @Param("ID") int ID
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE EMPLOYEE "+
        "SET ADDRESS = :ADDRESS "+
        "WHERE ID_EMPLOYEE = :ID",
        nativeQuery = true
    )
    public void UpdateEmployeeAddress (
        @Param("ADDRESS") String address,
        @Param("ID") int ID
    );
}
