package com.BancoDeSangre1.BancoDeSangre1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class controllerGral {
    
    @GetMapping("/")
    public String inicio(){
        return "index";
    }
}
