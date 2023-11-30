package project.projectucvwebsystem.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import project.projectucvwebsystem.dto.ProductDTO;
import project.projectucvwebsystem.entity.repository.ProductRepository;

@Service
@AllArgsConstructor
public class StockReportService {
    
    @Autowired
    ProductRepository productRepository;

    private List<ProductDTO> convertToProductDTOList(List<Object[]> rawDataList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        
        for (Object[] obj : rawDataList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId_product(((Number) obj[0]).longValue());
            productDTO.setAlias((String) obj[1]);
            productDTO.setName_large((String) obj[2]);
            productDTO.setQuantity(((Number) obj[3]).intValue());
            productDTO.setUnit_price(((Number) obj[4]).doubleValue());

            productDTOList.add(productDTO);
        }

        return productDTOList;
    }

    public List<ProductDTO> exportData() {
        return convertToProductDTOList(productRepository.DataAllProducts());
    }
}
