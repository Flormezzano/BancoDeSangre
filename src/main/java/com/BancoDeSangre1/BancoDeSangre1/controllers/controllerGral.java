<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
package com.BancoDeSangre1.BancoDeSangre1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
<<<<<<< HEAD
 * @author PCMONITOS
 */
@Controller
@RequestMapping("/")
public class controllerGral {
    
    @GetMapping("/Inicio")
    public String inicio(){
        return "index";
    }

=======
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
