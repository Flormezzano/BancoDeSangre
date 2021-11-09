/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BancoDeSangre1.BancoDeSangre1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontController {
    
 @GetMapping("/")
 public String index(){
    return "index";
 } 
 @GetMapping("/registrarse")
 public String modelRegistro(){
    return "modelRegistro";
 } 
 
 @GetMapping("/inicioUsuario")
 public String inicioUsuario(){
    return "inicioUsuario";
 } 
 
 @GetMapping("/donantes")
 public String listaDonantes(){
    return "listaDonantes";
 }
 
 @GetMapping("/nosotros")
 public String nosotros(){
    return "nosotros";
 }
 
 @GetMapping("/modelUsuario")
 public String modelUsuario(){
    return "modelUsuario";
 }
 
  @GetMapping("/error")
 public String error(){
    return "error";
 }
 
}
