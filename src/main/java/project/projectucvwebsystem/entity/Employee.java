package project.projectucvwebsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class Employee {
    @Id
    private int id_employee;
    private User user;
    private String email;
    private String names;
    private String lastname;
    private String address;
}
