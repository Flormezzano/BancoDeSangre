package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.MailService;
import static java.util.Date.from;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MailController {
    @Autowired
    private MailService mS;
    
    @PostMapping("/nosotros")
    public String nosotros(@RequestParam("name")String name, @RequestParam("mail")String mail, @RequestParam("subject")String subject, @RequestParam("body")String body){
        String mensaje = body + "\n Datos de contacto: " + "\nNombre: " + name + "\nE-mail: " + mail;
        String from= mail;
        String to= "factorvida2021@gmail.com";
        mS.enviar(from, to, subject, mensaje);
        return "nosotros";
    }
    
    
}
