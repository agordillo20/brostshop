package com.gordillo.adrian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gordillo.adrian.ReportService.ProductosReportService;
import com.gordillo.adrian.model.entity.Factura;
import com.gordillo.adrian.model.entity.Pedidos;
import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.service.FacturasService;
import com.gordillo.adrian.service.PedidosService;
import com.gordillo.adrian.service.ProductosService;
import com.gordillo.adrian.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/pedidos")
public class PedidosController {
    @Autowired
    private ProductosService productosService;
    @Autowired
    private PedidosService pedidosService;
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private FacturasService facturasService;
    @Autowired
    ProductosReportService productosReportService;

    @PostMapping(path = "/comprar")
    public ResponseEntity<?> getFiltro2(@RequestBody String carrito) throws Exception {
        List<Producto> productos = new ArrayList<>(new Gson().fromJson(carrito, new TypeToken<List<Producto>>() {
        }.getType()));//pasar de json a array de productos
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//Propiedades del usuario actual que tiene la sesion iniciada
        Pedidos pedido = new Pedidos("cod00" + pedidosService.lastId(), Date.from(Instant.now()), usuariosService.findByUsername(userDetail.getUsername()));
        pedidosService.save(pedido);
        int idPedido = pedidosService.getByCod(pedido.getCodigoPedido());
        for (Producto p : productos) {
            facturasService.save(new Factura(idPedido, p.getId()));
        }
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("pedidos", pedidosService.getByUsuario(usuariosService.findByUsername(userDetail.getUsername()).getId()));
        return "/public/Users/listAll";
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<byte[]> detalles(@PathVariable int id) {
        List<Producto> productos = new ArrayList<>();
        List<Factura> facturas = facturasService.getByIdPedido(id);
        for (Factura f : facturas) {
            productos.add(productosService.findOne(f.getIdProducto()));
        }
        byte[] bytes = productosReportService.generateReportFromJrxml(productos, "productos1.jrxml");
        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "inline; filename=" + "factura" + ".pdf")
                .body(bytes);
    }
}
