package com.gordillo.adrian.controller;

import com.gordillo.adrian.ReportService.FacturaReportService;
import com.gordillo.adrian.service.FacturasService;
import com.gordillo.adrian.service.PedidosService;
import com.gordillo.adrian.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/admin/rest/factura")
public class FacturasAdminRestController {
    @Autowired
    FacturaReportService facturaReportService;
    @Autowired
    FacturasService facturasService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    PedidosService pedidosService;


    @GetMapping(path = "/report/{id}")
    public ResponseEntity<byte[]> report(@PathVariable int id) {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        byte[] bytes = facturaReportService.generateReportFromJrxml(facturasService.getByIdPedido(id), usuariosService.findByUsername(userDetail.getUsername()), pedidosService.getById(id));
        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "inline; filename=\"" + "factura" + ".pdf\"")
                .body(bytes);
    }
}
