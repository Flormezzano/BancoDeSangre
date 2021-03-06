package com.BancoDeSangre1.BancoDeSangre1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class loginController {
    
    
    @GetMapping("")
    public String login(Model model, @RequestParam(required = false) String error,@RequestParam(required = false) String mail,@RequestParam(required = false) String logout){
        if (error!=null) {
            model.addAttribute("error", "El usuario ingresado o la contraseña son incorrectas");
        }
        if (mail!=null) {
            model.addAttribute("mail", mail);
        }
        return "index";
    }
}
