package project.projectucvwebsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

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

    public int Size() {
        return productCharacteristicsList.size();
    }

    public int obtainQuantity (String key) {
        return productCharacteristicsList.get(key);
    }

    public List<Object[]> OverviewProducts () {
        List<Object[]> list = new ArrayList<>();
        for (Map.Entry<String, Integer> set : productCharacteristicsList.entrySet()) {
            Object[] array = new Object[2];
            array[0] = set.getKey();
            array[1] = set.getValue();
            
            list.add(array);
        }

        return list;
    }

    public String viewProducts () {
        
        StringJoiner strJoiner = new StringJoiner("%80");
        for (Map.Entry<String, Integer> set : productCharacteristicsList.entrySet()) {
            //System.out.println("{Key: "+set.getKey()+", "+"value:"+set.getValue()+"}");
            String key = set.getKey();

            if (key.contains(" ")) {
                key = key.replaceAll(" ", "%25");
            }
            strJoiner.add(key+"%0"+set.getValue());
        }

        return strJoiner.toString();
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
