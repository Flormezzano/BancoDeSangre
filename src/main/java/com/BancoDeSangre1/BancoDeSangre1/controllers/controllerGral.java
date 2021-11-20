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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class controllerGral {

    @Autowired
    PersonaService personaServ;
    @Autowired
    ProvinciaService provinciaServ;
    @Autowired
    CiudadService ciudadServ;
    @Autowired
    TipoDeSangreService tipoServ;

    @GetMapping("/")
    public String index(HttpSession session, ModelMap model) throws Exception {
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
            } else {
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

    @PostMapping("/")
    public String registro(ModelMap model, @ModelAttribute() Persona persona, RedirectAttributes redirectAttributes) {
        try {
            personaServ.Registro(persona);
            redirectAttributes.addFlashAttribute("ok", "Se ha registrado con éxito");
            return "redirect:/";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("persona", persona); // se usa para pasar los datos al otro controller/Metodo
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/#registro-modal";
        }
    }
    
        @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

//    @GetMapping("/error")
//    public String error() {
//        return "error";
//    }
    @GetMapping("/contrasenia")
    public String contrasenia() {
        return "contrasenia";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/inicioUsuario")
    public String inicioUsuario() {
        return "inicioUsuario";
    }
    @GetMapping("/bajaUsuario")
    public String bajaUsuario (){
        return "bajaUsuario";
    }
   @GetMapping("/pedirMail")
    public String pedirMail (){
        return "pedirMail";
    }
    
    
    @GetMapping("/listaDonantes")
    public String listaDonantes(Model model, @RequestParam(required = false) String provincia, @RequestParam(required = false) String ciudad, @RequestParam(required=false) String tipodesangre) {
            model.addAttribute("provincia", provinciaServ.listar());
            model.addAttribute("ciudad", ciudadServ.listar());
            model.addAttribute("tipodesangre", tipoServ.listar());
            model.addAttribute("personas", personaServ.listaPersona());
            
        try {
            if ((ciudad != null) || (provincia != null) || (tipodesangre != null)) {
                model.addAttribute("personas", personaServ.filtrar(provincia, ciudad, tipodesangre));
            } else {
                model.addAttribute("personas", personaServ.listaPersona());
            
            }
            return "listaDonantes";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "listaDonantes";
        }

}



