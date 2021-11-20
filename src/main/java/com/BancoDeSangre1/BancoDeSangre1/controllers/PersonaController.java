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



  @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/editar")

    public String editar(@RequestParam(required = true) String id, ModelMap model) {
        model.addAttribute("provincias", provinciaServ.listar());
        model.addAttribute("ciudades", ciudadServ.listar());
        model.addAttribute("sangre", tipoServ.listar());
            Persona persona = personaServ.personaPorId(id);
            if(id.equals(persona.getId())){
                model.addAttribute("persona", persona);
                return "inicioUsuario";
            }
        return "inicioUsuaruio";
    }
  @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/actualizar")
    public String iniciar(ModelMap model, HttpSession session, @ModelAttribute() Persona persona) {
        try {
            personaServ.modificar(persona);
            session.setAttribute("personasession", persona);
            model.addAttribute("provincias", provinciaServ.listar());
            model.addAttribute("ciudades", ciudadServ.listar());
            model.addAttribute("sangre", tipoServ.listar());
            
            return "inicioUsuario";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("provincias", provinciaServ.listar());
            model.addAttribute("ciudades", ciudadServ.listar());
            model.addAttribute("sangre", tipoServ.listar());
            model.addAttribute("persona", persona);
            model.addAttribute("error", e.getMessage());
            return "inicioUsuario";
        }
        
    }
    
     @GetMapping("/dardebaja")
    public String darseDeBaja(Model model, @RequestParam(required = true)String id){
      Persona persona= personaServ.personaPorId(id);
      personaServ.baja(id);
      model.addAttribute("persona", persona);
      return "inicioUsuario";
    }

  

}
