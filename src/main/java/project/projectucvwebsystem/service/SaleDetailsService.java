package project.projectucvwebsystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.projectucvwebsystem.entity.repository.SaleDetailsRepository;

@Service
public class SaleDetailsService {
    
    @Autowired
    SaleDetailsRepository saleDetailsRepository;

    public void InsertSaleDetails(
        String idSales,
        int idProduct,
        int quantity
    ) {
        saleDetailsRepository.InsertDetails(idSales, idProduct, quantity);
    }
}
