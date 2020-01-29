package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.service.DistribuidoresService;
import com.gordillo.adrian.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/admin/productos")
public class ProductosController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private DistribuidoresService distribuidoresService;

    @GetMapping(path = "/")
    @ResponseBody
    public String home() {
        return "Recurso no encontrado";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("produto", new Producto());
        model.addAttribute("proveedores", distribuidoresService.findAll());
        return "administration/Producto/newProduct";
    }
}
