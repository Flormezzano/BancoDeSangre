
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BancoDeSangre1.BancoDeSangre1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**

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
}
