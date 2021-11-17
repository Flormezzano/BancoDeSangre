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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

//    @PreAuthorize("hasAnyRole('ROLE_USER')")
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
//    @PreAuthorize("hasAnyRole('ROLE_USER')")
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
}
