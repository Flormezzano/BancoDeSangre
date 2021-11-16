package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.CiudadService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.PersonaService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.ProvinciaService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.TipoDeSangreService;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class controllerGral {

    @Autowired
    PersonaService personaServ;
    @Autowired
    ProvinciaService provinciaServ;
    @Autowired
    CiudadService ciudadServ;
    @Autowired
    TipoDeSangreService tipoServ;
    
//    @GetMapping("/nosotros")
//    public String nosotros() {
//        return "nosotros";
//    }
//
//    @GetMapping("/error")
//    public String error() {
//        return "error";
//    }
//
//    @GetMapping("/inicioUsuario")
//    public String inicioUsuario() {
//        return "inicioUsuario";
//    }

    @PreAuthorize("hasAnyRole('ROL_USER', 'ROL_ADMIN')")
    @GetMapping("")
    public String index(HttpSession session, Model model) throws Exception {
        Persona persona;
        persona = (Persona) session.getAttribute("persona");
        try {
            if (persona != null) {
                switch (persona.getRol()) {
                    case USER:
                        return "redirect:/inicioUsuario";
                    case ADMIN:
                        return "redirect:/";
                }
            }else{
                persona = new Persona();
            }
            model.addAttribute("persona", persona);
        model.addAttribute("provincias", provinciaServ.listar());
        model.addAttribute("ciudades", ciudadServ.listar());
        model.addAttribute("sangre", tipoServ.listar());
        } catch (Exception e) {
            throw new Exception("Lo siento");
        }
        
        return "index";
    }
}
