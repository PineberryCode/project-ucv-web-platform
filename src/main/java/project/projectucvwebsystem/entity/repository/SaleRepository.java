package project.projectucvwebsystem.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.projectucvwebsystem.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, String> {
    
}
