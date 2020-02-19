package com.gordillo.adrian.controller;

import com.gordillo.adrian.ReportService.ProductosReportService;
import com.gordillo.adrian.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/admin/rest/productos")
public class ProductosAdminRestController {
    @Autowired
    ProductosReportService productosReportService;
    @Autowired
    ProductosService productosService;

    @GetMapping(path = "/report")
    public ResponseEntity<byte[]> report() {
        byte[] bytes = productosReportService.generateReportFromJrxml(productosService.findAll(), "productos.jrxml");
        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8;")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "inline; filename=\"" + "factura" + ".pdf\"")
                .body(bytes);
    }
}
