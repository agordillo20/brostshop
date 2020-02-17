package com.gordillo.adrian.ReportService;

import com.gordillo.adrian.model.entity.Factura;
import com.gordillo.adrian.model.entity.Pedidos;
import com.gordillo.adrian.model.entity.Usuario;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacturaReportService {
    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] generateReportFromJrxml(List<Factura> factura, Usuario usuario, Pedidos pedidos) {
        boolean error = false;
        try {
            String reportPath = "c:/recursosJasper";
            // Carga y compila fichero de resources
            File fileReport = ResourceUtils.getFile("classpath:facturas.jrxml");
            JasperReport jrxmlReport = JasperCompileManager.compileReport(fileReport.getAbsolutePath());
            // Carga DataSource
            List<Object> a = new ArrayList<>();
            a.add(factura);
            a.add(usuario);
            a.add(pedidos);
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(a);
            // Añade parámetros
            Map<String, Object> parameters = new HashMap<>();
//			parameters.put("fechaListado", new Date());
            // Completa report desde .jrxml
            JasperPrint jasperPrint = JasperFillManager.fillReport(jrxmlReport, parameters, jrBeanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\factura.pdf");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            error = true;
        }
        return null;
    }
}
