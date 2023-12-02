package project.projectucvwebsystem.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import project.projectucvwebsystem.dto.ProductDTO;
import project.projectucvwebsystem.service.StockReportService;

@Controller
@RequestMapping("/restricted/control-panel")
public class StockReportController {
    
    @Autowired
    StockReportService stockReportService;

    @PostMapping("/stock-report-export-pdf")
    public ResponseEntity<byte[]> downloadReport () {
        try {
            List<ProductDTO> exportedData = stockReportService.exportData();

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

    private JasperPrint generateJasperPrint(List<ProductDTO> exportedData) throws JRException, FileNotFoundException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stockData", new JRBeanCollectionDataSource(exportedData));

        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(
            ResourceUtils.getFile("classpath:report/stock_report.jrxml").getAbsolutePath()
        ), parameters, new JREmptyDataSource());

        return jasperPrint;
    }

}
