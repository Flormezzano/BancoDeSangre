package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.CiudadService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.PersonaService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.ProvinciaService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.TipoDeSangreService;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import com.BancoDeSangre1.BancoDeSangre1.exception.ExceptionService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
   
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("persona", personaServ.listaPersona());
        return "listaDonantes";
    }
    
//    @GetMapping("/registrar") // NO FUNCIONA - ANDA EL DE CONTROLLERGRAL
//    public String registrar(ModelMap model) {
//        model.addAttribute("persona", new Persona());
//        model.addAttribute("provincias", provinciaServ.listar());
//        model.addAttribute("ciudades", ciudadServ.listar());
//        model.addAttribute("sangre", tipoServ.listar());
//        model.addAttribute("sexos", personaServ.sexo());
//        return "index";
//    }
//
//    @PostMapping("/registrar")
//        try {
//    public String registro(ModelMap model, @ModelAttribute() Persona persona, RedirectAttributes redirectAttributes) {
//            personaServ.Registro(persona);
//            return "inicioUsuario";
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("persona", persona); // se usa para pasar los datos al otro controller/Metodo
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/error";
//        }
//    }
   @GetMapping("/editar")
    public String editar(@RequestParam(required = true) String id, ModelMap model) {
        model.addAttribute("provincias", provinciaServ.listar());
        model.addAttribute("ciudades", ciudadServ.listar());
        model.addAttribute("sangre", tipoServ.listar());
            Persona persona = personaServ.personaPorId(id);
            if(id.equals(persona.getId())){
                model.addAttribute("persona", persona);
                return "modelUsuario";
            }
        return "inicioUsuaruio";
    }

    @PostMapping("/actualizar")
    public String iniciar(ModelMap model, HttpSession session, @RequestParam() String id, @ModelAttribute() Persona persona) {
        try {
            Persona pers = personaServ.personaPorId(id);
            personaServ.modificar(pers);
            session.setAttribute("personasession", pers);
            return "inicioUsuario";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("provincias", provinciaServ.listar());
            model.addAttribute("ciudades", ciudadServ.listar());
            model.addAttribute("sangre", tipoServ.listar());
            model.addAttribute("perfil", persona);
            model.put("error", e.getMessage());
            return "modelUsuario";
        }
    }

//    @GetMapping("/login")
//    public String login(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String mail,@RequestParam(required = false) String logout){
//        if (error != null) {
//            model.addAttribute("error", "El usuario ingresado o la contraseña son incorrectas");
//        }
//        if (mail != null) {
//            model.addAttribute("mail", mail);
//        }
//        return "inicioUsuario";
//    }
//    @PostMapping("/iniciarSesion")
//    public String iniciar(ModelMap model, @ModelAttribute() Persona persona) {
//        try {
//            System.out.println("+++++++++++++++++++++++++++++++++++++" + persona.getMail());
//            personaServ.iniciarSesion(persona);
//            return "inicioUsuario";
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("persona", persona);
//            model.put("error", e.getMessage());
//            return "index";
//        }
//    }
}
