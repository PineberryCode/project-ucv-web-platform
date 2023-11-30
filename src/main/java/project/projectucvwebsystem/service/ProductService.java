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

    public Long getProducIDByName (String name) {
        return productRepository.GetProductIDByName(name);
    }

    public List<Object[]> getNameLargeAndQuantities () {
        return productRepository.NameLargeAndQuantity();
    }

    public List<String> getNameLargeByCategory (String alias) {
        return productRepository.NameLargeByCategory(alias);
    };

    public List<Object[]> getNameAndQuantityFromProductByCategory (
        String category
    ) {
        return productRepository.DataStockForGraphic(category);
    }

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
        Long idProduct
    ) {
        productRepository.UpdateCategoryFromProduct(
            category, 
            idProduct
        );
    }

    public void UpdateNameLargeFromProduct (
        String nameLarge,
        Long idProduct
    ) {
        productRepository.UpdateNameLargeFromProduct (
            nameLarge, 
            idProduct
        );
    }

    public void UpdateQuantityFromProduct (
        int quantity,
        Long idProduct
    ) {
        productRepository.UpdateQuantityFromProduct(
            quantity, 
            idProduct
        );
    }

    public void UpdateUnitPriceFromProduct (
        double unitPrice,
        Long idProduct
    ) {
        productRepository.UpdateUnitPriceFromProduct(
            unitPrice, 
            idProduct
        );
    }

    public void RemoveStockUnits (
        int minus,
        Long idProduct
    ) {
        productRepository.RemoveProductQuantityByID(minus, idProduct);
    }
}
