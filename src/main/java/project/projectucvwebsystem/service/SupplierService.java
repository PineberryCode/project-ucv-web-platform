package project.projectucvwebsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.entity.repository.SupplierRepository;

@Service
@AllArgsConstructor
public class SupplierService {
    
    @Autowired
    private final SupplierRepository supplierRepository;

    public List<Object[]> findAllSuppliers () {
        return supplierRepository.findAllSuppliers();
    }

    public void DeleteOnlySupplier (int ID) {
        supplierRepository.DeleteOnlySupplier(ID);
    }

    public void CreateANewSupplier 
    (
        String name, 
        String number, 
        String email, 
        String address
    ) {
        supplierRepository.CreateANewSupplier(name, number, email, address);
    }

    public void UpdateSupplierDescriptionName (String name, int ID) {
        supplierRepository.UpdateSupplierDescriptionName(name, ID);
    }

    public void UpdateSupplierNumber (String number, int ID) {
        supplierRepository.UpdateSupplierContactNumber(number, ID);
    }

    public void UpdateSupplierEmail (String email, int ID) {
        supplierRepository.UpdateSupplierEmail(email, ID);
    }

    public void UpdateSupplierAddress (String address, int ID) {
        supplierRepository.UpdateSupplierAddress(address, ID);
    }

}
