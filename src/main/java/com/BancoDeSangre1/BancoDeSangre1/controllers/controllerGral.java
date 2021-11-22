package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.CiudadService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.MailService;
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
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    MailService ms;

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
            model.addAttribute("persona", persona);
            model.addAttribute("provincias", provinciaServ.listar());
            model.addAttribute("ciudades", ciudadServ.listar());
            model.addAttribute("sangre", tipoServ.listar());
            throw new Exception("Lo siento");
        }
        return "index";
    }

    @PostMapping("/")
    public String registro(ModelMap model, @ModelAttribute() Persona persona, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            personaServ.Registro(persona);
            redirectAttributes.addFlashAttribute("ok", "Se ha registrado con éxito");
            ms.enviarMail("Se ha registrado exitosamente a Factor Vida. Gracias por usar nuestro servicio.", "Registro Factor Vida", persona.getMail());
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("persona", persona); // se usa para pasar los datos al otro controller/Metodo
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            model.addAttribute("provincias", provinciaServ.listar());
            model.addAttribute("ciudades", ciudadServ.listar());
            model.addAttribute("sangre", tipoServ.listar());
            return "redirect:/";
        }
    }

    @GetMapping("/recuperar-contra/{id}")
    public String recuperarContra(ModelMap model, @PathVariable String id, RedirectAttributes ra) {
        try {
            System.out.println(personaServ.personaPorId(id));
            model.put("persona", id);
            return "contrasenia";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorContrasenia", e.getMessage());
            return "contrasenia";
        }

    }

    @PostMapping("/recuperar-contra/{id}")
    public String buscarMail(@PathVariable String id, Model model, @RequestParam String password, @RequestParam String password2, RedirectAttributes ra) {
        try {
            personaServ.cambiarContrasenia(id, password, password2);
            ra.addAttribute("ok", "Su contraseña ha sido modificada");
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorContrasenia", e.getMessage());
            return "contrasenia";
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/inicioUsuario")
    public String inicioUsuario(HttpSession session, Model model) throws Exception {
        Persona persona;
        persona = (Persona) session.getAttribute("personasession");
        try {
            model.addAttribute("persona", persona);
            model.addAttribute("provincias", provinciaServ.listar());
            model.addAttribute("ciudades", ciudadServ.listar());
            model.addAttribute("sangre", tipoServ.listar());
        } catch (Exception e) {
            throw new Exception("Lo siento");
        }
        return "inicioUsuario";
    }

    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/listaDonantes")
    public String listaDonantes(Model model, @RequestParam(required = false) String provincia, @RequestParam(required = false) String ciudad, @RequestParam(required = false) String tipodesangre) {
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
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/bajaUsuario")
    public String bajaUsuario (){
        return "bajaUsuario";
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/altaUsuario")
    public String altaUsuario (){
        return "altaUsuario";
    }
}
