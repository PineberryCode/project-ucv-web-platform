package project.projectucvwebsystem.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import project.projectucvwebsystem.dto.SaleDTO;
import project.projectucvwebsystem.service.SaleReportService;

@Controller
@RequestMapping("/restricted/control-panel")
public class SaleReportController {
    
    @Autowired
    SaleReportService saleReportService;

    @PostMapping("/sale-report-export-pdf")
    public ResponseEntity<byte[]> downloadSaleReport() {
        try {

            List<SaleDTO> exportedData = saleReportService.exportSaleData();

            JasperPrint jasperPrint = generateJasperPrint(exportedData);

            byte[] pdfByteArray = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "stock-report.pdf");

            return new ResponseEntity<>(pdfByteArray, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private JasperPrint generateJasperPrint(List<SaleDTO> exportedData) throws JRException, FileNotFoundException {
    
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("saleData", new JRBeanCollectionDataSource(exportedData));

        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(
            ResourceUtils.getFile("classpath:report/sale_report.jrxml").getAbsolutePath()
        ), parameters, new JREmptyDataSource());

        return jasperPrint;
    }

}
