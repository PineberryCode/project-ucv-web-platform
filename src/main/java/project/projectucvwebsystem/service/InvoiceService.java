package project.projectucvwebsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import project.projectucvwebsystem.entity.Invoice;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceService extends Invoice {

    Invoice invoice;

    public void addProduct (
        String productName, 
        int quantity
    ) {
        productCharacteristicsList.put(productName, quantity);
    }

    public void viewProducts () {
        for (Map.Entry<String, Integer> set : productCharacteristicsList.entrySet()) {
            System.out.println("{Key: "+set.getKey()+", "+"value:"+set.getValue()+"}");
        }
    }

    public void removeProduct (
        String productName
    ) {
        productCharacteristicsList.remove(productName);
    }

    public void removeAllProducts () {
        for (Map.Entry<String, Integer> set : productCharacteristicsList.entrySet()) {
            productCharacteristicsList.remove(set.getKey());
        }
    }

}
