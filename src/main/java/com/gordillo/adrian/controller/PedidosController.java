package com.gordillo.adrian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gordillo.adrian.model.entity.Factura;
import com.gordillo.adrian.model.entity.Pedidos;
import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.service.FacturasService;
import com.gordillo.adrian.service.PedidosService;
import com.gordillo.adrian.service.ProductosService;
import com.gordillo.adrian.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping(path = "/comprar")
    public ResponseEntity<String> getFiltro2(@RequestBody String carrito) throws Exception {
        List<Producto> productos = new ArrayList<>(new Gson().fromJson(carrito, new TypeToken<List<Producto>>() {
        }.getType()));//pasar de json a array de productos
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//Propiedades del usuario actual que tiene la sesion iniciada
        Pedidos pedido = new Pedidos("cod00" + pedidosService.lastId(), Date.from(Instant.now()), usuariosService.findByUsername(userDetail.getUsername()));
        pedidosService.save(pedido);
        int idPedido = pedidosService.getByCod(pedido.getCodigoPedido());
        for (Producto p : productos) {
            facturasService.save(new Factura(idPedido, p.getId()));
        }
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("pedidos", pedidosService.getByUsuario(usuariosService.findByUsername(userDetail.getUsername()).getId()));
        return "/public/Users/listAll";
    }
}
