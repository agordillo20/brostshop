package com.gordillo.adrian.ReportService;

import com.gordillo.adrian.service.ProductosService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductosReportService {
    @Autowired
    private ProductosService productosService;

    public byte[] generateReportFromJrxml() {
        boolean error = false;
        try {
            String reportPath = "d:/recursosJasper";
            // Carga y compila fichero de resources
            File fileReport = ResourceUtils.getFile("classpath:productos.jrxml");
            JasperReport jrxmlReport = JasperCompileManager.compileReport(fileReport.getAbsolutePath());
            // Carga DataSource
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(productosService.findAll());
            // Añade parámetros
            Map<String, Object> parameters = new HashMap<>();
            //parameters.put("fecha",pedidos.getFechaPedido());
            // Completa report desde .jrxml
            JasperPrint jasperPrint = JasperFillManager.fillReport(jrxmlReport, parameters, jrBeanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\listado.pdf");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            error = true;
        }
        return null;
    }
}
