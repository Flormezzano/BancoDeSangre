package com.BancoDeSangre1.BancoDeSangre1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
@Controller
@RequestMapping("")
public class controllerGral {
    
    @GetMapping("/")
    public String inicio(){
        return "index";
    }
  
    
    
=======
/**
 *
 * @author Gastón
 */

@Controller
@RequestMapping("/")
public class controllerGral {

    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
    
    @GetMapping("/error")
    public String error(){
        return "error";
    }
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
}
