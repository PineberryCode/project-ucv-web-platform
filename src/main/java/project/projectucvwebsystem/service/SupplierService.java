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
}
