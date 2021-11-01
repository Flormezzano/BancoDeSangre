package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaController ps;

    @GetMapping("/registrar")
    public String registrar() {
        return "modelRegistro";
    }

    @PostMapping("/registrar")
    public String registro(Model model, @RequestParam Persona persona) {
        try {
            ps.registro(model, persona);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "modelRegistro";
        }
    }
    

}
