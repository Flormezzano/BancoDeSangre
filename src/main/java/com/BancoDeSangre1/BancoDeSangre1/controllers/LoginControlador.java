package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.MailService;
import com.BancoDeSangre1.BancoDeSangre1.Servicios.PersonaService;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Gastón
 */
@Controller
@RequestMapping("/login")
public class LoginControlador {

    @Autowired
    PersonaService ps;

    @Autowired
    MailService ms;

    @GetMapping("")
    public String login(RedirectAttributes ra, Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String mail, @RequestParam(required = false) String logout) {
        if (error != null) {
            model.addAttribute("errorInicio", "El usuario ingresado o la contraseña son incorrectas");
        }
        if (mail != null) {
            model.addAttribute("mail", mail);
        }
        if (logout != null) {
            model.addAttribute("logout", "Ha finalizado sesión");
        }
        model.addAttribute("persona", new Persona());
        return "index";
    }

    @GetMapping("/mail-recuperacion")
    public String pedirMailGet() {
        return "pedirMail";
    }

    @PostMapping("/mail-recuperacion")
    public String pedirMail(RedirectAttributes ra, @RequestParam String mail, Model model) {
        try {

            Persona persona = ps.buscarPorMail(mail);

            ms.enviarMail("Por favor ingrese al siguiente link antes de 48hs y escriba una nueva contraseña: "
                    + "http://localhost:8080/recuperar-contra/"+persona.getId(), "Factor Vida: cambio de contraseña", persona.getMail());
            ra.addFlashAttribute("ok", "se envio un email para cambiar su contraseña, recuerde que el link expira");
            return "redirect:/";
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }

}
