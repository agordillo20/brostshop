package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Marca;
import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.service.MarcasService;
import com.gordillo.adrian.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/marca")
public class MarcaController {
    @Autowired
    private MarcasService marcasService;
    @Autowired
    private ProductosService productosService;

    @GetMapping(path = "/")
    @ResponseBody
    public String home() {
        return "Recurso no encontrado";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("marca", new Marca());
        return "administration/Marca/newMarca";
    }

    @PostMapping(path = "/add")
    public String add(@Valid Marca marca, BindingResult result, Model model, RedirectAttributes flash) {
        try {
            marcasService.save(marca);
            flash.addAttribute("success", "La marca ha sido añadida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/admin/marca/add";
    }

    @GetMapping("/list")
    public String listado(Model model) {
        model.addAttribute("marcas", marcasService.findAll());
        return "administration/Marca/listAll";
    }


    @PostMapping("/filtro")
    public ResponseEntity<List<Producto>> getFiltro(@RequestBody String marca[]) {
        List<Producto> productos = new ArrayList<>();
        for (String mar : marca) {
            productos.addAll(marcasService.findAllByRazonSocial(mar));
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping(path = "/remove/{id}")
    public String remove(@PathVariable Integer id, Model model) {
        marcasService.delete(id);
        return "redirect:/admin/marca/list";
    }
}
