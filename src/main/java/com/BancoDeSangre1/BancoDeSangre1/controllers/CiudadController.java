package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.CiudadService;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Ciudad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Gastón
 */
@Controller
@RequestMapping("/ciudad")
public class CiudadController {

//    @Autowired
//    private CiudadService ciudadServicio;
//    
//    @GetMapping("/lista")
//    public String lista(Model model, @RequestParam(required = false) String id){
//        if (id != null) {
//            List<Ciudad> provincia = ciudadServicio.listaCiudadPorProvincia(id);
//            model.addAttribute("provincia", provincia);
//        } else {
//            List<Ciudad> provincia = ciudadServicio.listaCiudad();
//            model.addAttribute("provincia", provincia);
//        }
//        return "indexC";
//    }
}
