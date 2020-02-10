package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.service.DistribuidoresService;
import com.gordillo.adrian.service.MarcasService;
import com.gordillo.adrian.service.ProductosService;
import com.gordillo.adrian.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("ram", productosService.findRam());
        model.addAttribute("almacenamiento", productosService.findMemoria());
        model.addAttribute("So", productosService.findSO());
        return "public/Producto/listAll";
    }

    @GetMapping(path = "/remove/{id}")
    public String remove(@PathVariable Integer id) {
        productosService.delete(id);
        return "redirect:/admin/productos/list";
    }

    @PostMapping("/filtroRam")
    public ResponseEntity<List<Producto>> getFiltro(@RequestBody String ram[]) {
        List<Producto> productos = new ArrayList<>();
        for (String mar : ram) {
            productos.addAll(productosService.filtroRam(Integer.parseInt(mar)));
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/filtroProcesador")
    public ResponseEntity<List<Producto>> getFiltro1(@RequestBody String procesador[]) {
        List<Producto> productos = new ArrayList<>();
        for (String mar : procesador) {
            productos.addAll(productosService.filtroProcesador(mar));
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/filtroSo")
    public ResponseEntity<List<Producto>> getFiltro2(@RequestBody String so[]) {
        List<Producto> productos = new ArrayList<>();
        for (String mar : so) {
            productos.addAll(productosService.filtroSo(mar));
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/filtroAli")
    public ResponseEntity<List<Producto>> getFiltro3(@RequestBody String ali[]) {
        List<Producto> productos = new ArrayList<>();
        for (String mar : ali) {
            productos.addAll(productosService.filtroAli(Integer.parseInt(mar)));
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/addCarrito")
    public ResponseEntity<Producto> addCarro(@RequestBody int id) {
        Producto producto = productosService.findOne(id);
        return ResponseEntity.ok(producto);
    }
}
