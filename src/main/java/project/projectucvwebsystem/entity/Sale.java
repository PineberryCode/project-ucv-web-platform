package project.projectucvwebsystem.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SALE")
@Getter
@Setter
public class Sale {
    @Id
    private String id_sales;
    @OneToOne
    @JoinColumn(name = "id_employee")
    private Employee id_employee;
    private LocalDate date_sale;
    private String client_surname;
    private String client_contact_number;
    private double igv;
}
