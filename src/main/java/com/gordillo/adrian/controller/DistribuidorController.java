package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Distribuidor;
import com.gordillo.adrian.service.DistribuidoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/admin/distribuidor")
public class DistribuidorController {
    @Autowired
    private DistribuidoresService distribuidoresService;

    @GetMapping(path = "/")
    @ResponseBody
    public String home() {
        return "Recurso no encontrado";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("distribuidor", new Distribuidor());
        return "administration/Distribuidor/newDistribuidor";
    }

    @PostMapping(path = "/add")
    public String add(@Valid Distribuidor distribuidor, BindingResult result, Model model) {
        try {
            distribuidoresService.save(distribuidor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/admin/distribuidor/add";
    }

    @GetMapping("/list")
    public String listado(Model model) {
        model.addAttribute("distribuidores", distribuidoresService.findAll());
        return "administration/Distribuidor/listAll";
    }

    @GetMapping(path = "/remove/{id}")
    public String remove(@PathVariable Integer id, Model model) {
        distribuidoresService.delete(id);
        return "redirect:/admin/distribuidor/list";
    }
}
