package project.projectucvwebsystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.dto.SaleDTO;
import project.projectucvwebsystem.entity.repository.SaleRepository;

@Service
@AllArgsConstructor
public class SaleReportService {
    
    @Autowired
    SaleRepository saleRepository;

    private List<SaleDTO> convertToSaleDTOList(List<Object[]> saleQueryList) {
        
        List<SaleDTO> saleDTOList = new ArrayList<>();

        for (Object[] obj : saleQueryList) {
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.setId_sales( (String) obj[0]);
            saleDTO.setId_employee( ((Number) obj[1]).longValue() );
            saleDTO.setDate_sale( (Date) obj[2] );
            saleDTO.setName_large( (String) obj[3] );
            saleDTO.setTotal( ((Number) obj[4]).doubleValue() );

            saleDTOList.add(saleDTO);
        }

        return saleDTOList;
    }

    public List<SaleDTO> exportSaleData() {
        return convertToSaleDTOList(saleRepository.saleReport());
    }
}
