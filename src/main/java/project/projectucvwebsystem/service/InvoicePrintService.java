package project.projectucvwebsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.dto.InvoiceDTO;
import project.projectucvwebsystem.entity.repository.InvoicePrintRepository;

@Service
//@AllArgsConstructor
public class InvoicePrintService {
    
    @Autowired
    InvoicePrintRepository invoicePrintRepository;

    //@Autowired

    private List<InvoiceDTO> convertToInvoiceDTOList (List<Object[]> rowList) {
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();

        for (Object[] row : rowList) {
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setName_large_product( (String) row[0] );
            invoiceDTO.setQuantity( ((Number) row[1]).intValue() );
            invoiceDTO.setUnit_price( ((Number) row[2]).doubleValue() );
            invoiceDTO.setTotal( ((Number) row[3]).doubleValue() );

            invoiceDTOList.add(invoiceDTO);
        }

        return invoiceDTOList;
    }

    private InvoiceDTO convertToInvoiceDTO (String dataGeneral) {
        
        String[] dataSplitted = dataGeneral.split(",");
        System.out.println("aaaaa: "+dataSplitted[3]);
        
        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setId_sales( (String) dataSplitted[0] );
        invoiceDTO.setSurname( (String) dataSplitted[1] );
        invoiceDTO.setClient_cel((String) dataSplitted[2] );
        double d = Double.parseDouble(dataSplitted[3]);
        invoiceDTO.setIgv( ((Number) d).doubleValue());  

        return invoiceDTO;
    }

    public List<InvoiceDTO> getDataForInvoiceTable (
        String idSales
    ) {
        return convertToInvoiceDTOList(invoicePrintRepository.getProductInTable(idSales));
    }

    public InvoiceDTO getGeneralForInvoice () {
        //String[] resultGeneralArray = invoicePrintRepository.getGeneralForInvoice().split(",");
        return convertToInvoiceDTO(invoicePrintRepository.getGeneralForInvoice()) ;
    }

}
