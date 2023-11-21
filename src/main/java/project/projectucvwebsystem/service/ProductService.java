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

    public String[] getCategories () {
        return productRepository.Categories();
    }

    public List<Object[]> getNameLargeAndQuantities () {
        return productRepository.NameLargeAndQuantity();
    }

    public List<String> getNameLargeByCategory (String alias) {
        return productRepository.NameLargeByCategory(alias);
    };

    public List<Object[]> DataAllProducts () {
        return productRepository.DataAllProducts();
    }

    public void InsertNewProduct (
        int idCategory,
        String nameProduct,
        int quantity,
        double unitPrice
        ) {
        productRepository.InsertNewProduct(
            idCategory, 
            nameProduct, 
            quantity, 
            unitPrice
        );
    }

    public void UpdateCategoryFromProduct (
        String category,
        int idProduct
    ) {
        productRepository.UpdateCategoryFromProduct(
            category, 
            idProduct
        );
    }

    public void UpdateNameLargeFromProduct (
        String nameLarge,
        int idProduct
    ) {
        productRepository.UpdateNameLargeFromProduct (
            nameLarge, 
            idProduct
        );
    }

    public void UpdateQuantityFromProduct (
        int quantity,
        int idProduct
    ) {
        productRepository.UpdateQuantityFromProduct(
            quantity, 
            idProduct
        );
    }

    public void UpdateUnitPriceFromProduct (
        double unitPrice,
        int idProduct
    ) {
        productRepository.UpdateUnitPriceFromProduct(
            unitPrice, 
            idProduct
        );
    }
}
