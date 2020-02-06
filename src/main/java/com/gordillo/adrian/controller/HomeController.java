package com.gordillo.adrian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping(path = "/")
public class HomeController {
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
}
