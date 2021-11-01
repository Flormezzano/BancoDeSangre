package com.BancoDeSangre1.BancoDeSangre1.controllers;

<<<<<<< HEAD
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
=======
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
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< HEAD
=======
/**
 *
 * @author Gastón
 */
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
<<<<<<< HEAD
    PersonaController ps;

    @GetMapping("/registrar")
    public String registrar() {
=======
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
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
        return "modelRegistro";
    }

    @PostMapping("/registrar")
<<<<<<< HEAD
    public String registro(Model model, @RequestParam Persona persona) {
        try {
            ps.registro(model, persona);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
=======
    public String registro(ModelMap model, @RequestParam Persona persona) {
        try {
            personaServ.Registro(persona);
            return "inicioUsuario";
        } catch (Exception e) {
            model.addAttribute("persona", persona);
            model.put("error", e.getMessage());
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
            return "modelRegistro";
        }
    }
    
<<<<<<< HEAD

}
=======
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
    
    @GetMapping("/iniciarSesion")
    public String iniciarSesion() {
        return "index";
    }
    
    @PostMapping("/iniciarSesion")
    public String iniciar(ModelMap model, @RequestParam Persona persona) {
        try {
            personaServ.iniciarSesion(persona);
            return "inicioUsuario";
        } catch (Exception e) {
            model.addAttribute("persona", persona);
            model.put("error", e.getMessage());
            return "index";
        }
    }
}
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
