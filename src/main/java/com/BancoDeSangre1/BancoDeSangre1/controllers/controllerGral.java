package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class controllerGral {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
    
    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/inicioUsuario")
    public String inicioUsuario(){
        return "inicioUsuario";
    }
    
    @GetMapping("")
    public String index(HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
        Persona persona = (Persona) session.getAttribute("persona");
        try{
            if(persona != null){
                switch(persona.getRol()){
                    case USER:
                        return "redirect:/inicioUsuario";
                    case ADMIN:
                        return "redirect:/";
                }
            }
        }catch(Exception e){
            throw new Exception("Lo siento");
        }
        return "index";
    }
}