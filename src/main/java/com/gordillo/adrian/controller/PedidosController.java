package com.gordillo.adrian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gordillo.adrian.model.entity.Pedidos;
import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.model.entity.Usuario;
import com.gordillo.adrian.service.PedidosService;
import com.gordillo.adrian.service.ProductosService;
import com.gordillo.adrian.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Type;
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

    @PostMapping(path = "/comprar")
    public String getFiltro2(@RequestBody String carrito) throws Exception {
        Type p = new TypeToken<List<Producto>>() {
        }.getType();
        List<Producto> productos = new ArrayList<>(new Gson().fromJson(carrito, p));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Usuario user = usuariosService.findByUsername(userDetail.getUsername());
        pedidosService.save(new Pedidos(Date.from(Instant.now()), productos, user));
        return "/";
    }

}
