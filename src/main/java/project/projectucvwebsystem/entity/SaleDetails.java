package project.projectucvwebsystem.entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SALE_DETAILS")
@Getter
@Setter
public class SaleDetails {
    @Id
    private int id_sale_details;
    @OneToOne
    @JoinColumn(name = "id_sales")
    private Sale id_sales;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product id_product;
    private int required_quantity;
}
