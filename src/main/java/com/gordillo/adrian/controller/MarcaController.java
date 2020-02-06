package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Marca;
import com.gordillo.adrian.service.MarcasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/admin/marca")
public class MarcaController {
    @Autowired
    private MarcasService marcasService;

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
        model.addAttribute("distribuidores", marcasService.findAll());
        return "administration/Marca/listAll";
    }

    @GetMapping(path = "/remove/{id}")
    public String remove(@PathVariable Integer id, Model model) {
        marcasService.delete(id);
        return "redirect:/admin/marca/list";
    }
}
