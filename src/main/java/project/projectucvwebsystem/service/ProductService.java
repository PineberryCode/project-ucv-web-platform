package project.projectucvwebsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.entity.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
    
    @Autowired
    private final ProductRepository productRepository;

    public String[] getCategories () {return productRepository.Categories();}

    public List<Object[]> getNameLargeAndQuantities () {return productRepository.NameLargeAndQuantity();}

    public List<String> getNameLargeByCategory (String alias) {
        return productRepository.NameLargeByCategory(alias);
    };
}
