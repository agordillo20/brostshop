package com.gordillo.adrian.controller;

import com.gordillo.adrian.service.PedidosService;
import com.gordillo.adrian.service.ProductosService;
import com.mysql.cj.xdevapi.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin/pedidos")
public class PedidosController {
    @Autowired
    private ProductosService productosService;
    @Autowired
    private PedidosService pedidosService;

    @PostMapping(path = "/comprar")
    public ResponseEntity<String> getFiltro2(@RequestBody String carrito) {
        JsonParser.parseDoc(carrito);
        return ResponseEntity.ok(productos);
    }

}
