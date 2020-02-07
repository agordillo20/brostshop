package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.service.DistribuidoresService;
import com.gordillo.adrian.service.MarcasService;
import com.gordillo.adrian.service.ProductosService;
import com.gordillo.adrian.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(path = "/admin/productos")
public class ProductosController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private DistribuidoresService distribuidoresService;
    @Autowired
    private ProductosService productosService;
    @Autowired
    private MarcasService marcasService;

    @GetMapping(path = "/")
    @ResponseBody
    public String home() {
        return "Recurso no encontrado";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("produto", new Producto());
        model.addAttribute("distribuidores", distribuidoresService.findAll());
        model.addAttribute("marcas", marcasService.findAll());
        return "administration/Producto/newProduct";
    }

    @PostMapping(path = "/add")
    public String add(@Valid Producto producto, BindingResult result, Model model,
                      @RequestParam("file") MultipartFile foto, RedirectAttributes flash) {
        String destino;
        if (result.hasErrors()) {
            destino = "administration/Producto/newProduct";
        } else {
            if (!foto.isEmpty()) {
                if (producto.getId() != null && producto.getId() > 0 && producto.getFoto() != null
                        && producto.getFoto().length() > 0) {
                    uploadFileService.delete(producto.getFoto());
                }
                String uniqueFilename = null;
                try {
                    uniqueFilename = uploadFileService.copy(foto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                producto.setFoto(uniqueFilename);
            }
            try {
                productosService.save(producto);
                flash.addFlashAttribute("success", "El producto ha sido creado");
            } catch (Exception e) {
                flash.addFlashAttribute("error", "El producto no ha sido creado.");
            }
            destino = "redirect:/admin/productos/add";
        }
        return destino;
    }

    @GetMapping("/list")
    public String listado(Model model) {
        model.addAttribute("productos", productosService.findAll());
        return "administration/Producto/listAll";
    }

    @GetMapping("/list1")
    public String listado1(Model model) {
        model.addAttribute("productos", productosService.findAll());
        model.addAttribute("marcas", marcasService.findAll());
        model.addAttribute("procesadores", productosService.findProcesadores());
        return "public/Producto/listAll";
    }

    @GetMapping(path = "/remove/{id}")
    public String remove(@PathVariable Integer id) {
        productosService.delete(id);
        return "redirect:/admin/productos/list";
    }

    @GetMapping(path = "/filtroMarca{id}")
    public String filtroMarca(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("productos", productosService.findProductosByMarca(id));
        return "administration/Producto/filtroMarca";
    }
}
