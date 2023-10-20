package project.projectucvwebsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SUPPLIER")
@Getter
@Setter
public class Supplier {
    @Id
    private Long id_supplier;
    private String description_name;
    private String contact_number;
    private String email;
    private String address;
}
