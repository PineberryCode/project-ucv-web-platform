package project.projectucvwebsystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.projectucvwebsystem.entity.repository.SaleRepository;

@Service
public class SaleService {
    
    @Autowired
    SaleRepository saleRepository;

    public String InsertSaleAndReturnID (
        int idEmployee,
        String clientSurname,
        String clientPhone
    ) {
        int counter = saleRepository.CountRows()+1;

        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);
        String idSales = String.format("%010d%s", counter, currentDate);//currentDate.toString();

        double igv = 0.18;


        saleRepository.InsertSale(idSales, idEmployee, sqlDate, clientSurname, clientPhone, igv);

        return idSales;
    }

    public String[] showUniquePriceByProduct (
        int requiredQuantity,
        String nameLarge
    ) {
        return saleRepository.showUniquePriceByProduct(requiredQuantity, nameLarge);
    }
}
