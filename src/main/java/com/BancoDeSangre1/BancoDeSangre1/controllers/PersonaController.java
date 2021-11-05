package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.CiudadService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.PersonaService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.ProvinciaService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.TipoDeSangreService;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import com.BancoDeSangre1.BancoDeSangre1.exception.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaServ;
    @Autowired
    ProvinciaService provinciaServ;
    @Autowired
    CiudadService ciudadServ;
    @Autowired
    TipoDeSangreService tipoServ;
    
    @GetMapping("/lista")
    public String lista(Model model){
        model.addAttribute("persona", personaServ.listaPersona());
        return"listaDonantes";
    }
    
    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("provincias", provinciaServ.listar());
        model.addAttribute("ciudades", ciudadServ.listar());
        model.addAttribute("sangre", tipoServ.listar());
        return "modelRegistro";
    }

    @PostMapping("/registrar")
    public String registro(ModelMap model, @ModelAttribute("persona") Persona persona) {
        try {
            personaServ.Registro(persona);
            return "inicioUsuario";
        } catch (Exception e) {
            model.addAttribute("persona", persona);
            model.put("error", e.getMessage());
            return "modelRegistro";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap model, Persona persona) {
        try {
            Persona pers = personaServ.personaPorId(persona);
            personaServ.modificar(pers);
            model.addAttribute("libro", pers);
            return "modelUsuario";
        } catch (ExceptionService ex) {
            model.put("error", ex.getMessage());
            return "modelUsuario";
        }
    }
    
    @GetMapping("/login")
    public String login(Model model, @RequestParam Persona persona, @RequestParam(required = false) String error,@RequestParam(required = false) String mail,@RequestParam(required = false) String logout){
        if (error!=null) {
            model.addAttribute("persona", persona);
            model.addAttribute("error", "El usuario ingresado o la contraseña son incorrectas");
        }
        if (mail!=null) {
            model.addAttribute("mail", mail);
        }
        return "index";
    }
    
//    @PostMapping("/iniciarSesion")
//    public String iniciar(ModelMap model, @RequestParam Persona persona) {
//        try {
//            personaServ.iniciarSesion(persona);
//            return "inicioUsuario";
//        } catch (Exception e) {
//            model.addAttribute("persona", persona);
//            model.put("error", e.getMessage());
//            return "index";
//        }
//    }
}