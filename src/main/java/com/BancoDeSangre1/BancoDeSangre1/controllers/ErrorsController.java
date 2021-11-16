package com.BancoDeSangre1.BancoDeSangre1.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Gastón
 */
@Controller
public class ErrorsController implements ErrorController{

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String paginaDeError(Model model,HttpServletRequest httpservletrequest){
        String mensajeError = "";
        int codError = (int) httpservletrequest.getAttribute("javax.servlet.error.status_code");
        switch(codError){
            case 400:
                mensajeError = "El recurso solicitado no exíste";
                break;
            case 401:
                mensajeError = "No tiene autorizacion para visitar esta página";
                break;
            case 403:
                mensajeError = "No tiene permiso para visitar esta página";
                break;
            case 404:
                mensajeError = "No se encuentra la página";
                break;
            case 500:
                mensajeError = "El servidor no pudo ejecutar la petición";
                break;
            default:
                
        }
        model.addAttribute("codigo", codError);
        model.addAttribute("mensaje", mensajeError);
        return "error";
    }
}