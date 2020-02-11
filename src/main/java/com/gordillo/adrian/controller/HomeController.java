package com.gordillo.adrian.controller;

import com.gordillo.adrian.model.entity.Rol;
import com.gordillo.adrian.model.entity.Usuario;
import com.gordillo.adrian.service.UploadFileService;
import com.gordillo.adrian.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model, Principal principal,
                        RedirectAttributes flash) {
        if (principal != null) {
            flash.addFlashAttribute("info", "Tiene una sesion iniciada");
            return "redirect:/";
        }
        if (error != null) {
            flash.addAttribute("error", "Identificacion incorrecta");
            model.addAttribute("error", "Identificación incorrecta");
        }
        return "login";
    }

    @GetMapping("registrarse")
    public String registrar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/public/Users/Registro";
    }

    @PostMapping(path = "registrarse")
    public String add(@Valid Usuario usuario, BindingResult result, Model model,
                      @RequestParam("file") MultipartFile foto, RedirectAttributes flash) {
        String destino;
        if (result.hasErrors()) {
            destino = "administration/Producto/newProduct";
        } else {
            if (!foto.isEmpty()) {
                if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
                        && usuario.getFoto().length() > 0) {
                    uploadFileService.delete(usuario.getFoto());
                }
                String uniqueFilename = null;
                try {
                    uniqueFilename = uploadFileService.copy(foto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                usuario.setFoto(uniqueFilename);
            }
            try {
                List<Rol> roles = new ArrayList<>();
                roles.add(new Rol("ROLE_USER"));
                usuario.setRoles(roles);
                usuario.setPassword(encoder.encode(usuario.getPassword()));
                usuariosService.save(usuario);
                flash.addFlashAttribute("success", "El usuario ha sido creado");
            } catch (Exception e) {
                flash.addFlashAttribute("error", "El usuario no ha sido creado.");
            }
            destino = "redirect:/login";
        }
        return destino;
    }

    @GetMapping("carrito")
    public String carrito() {
        return "/public/Users/Carrito";
    }
}
