package com.BancoDeSangre1.BancoDeSangre1.Controladores;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProvinciaControlador {

    @Autowired
    ProvinciaService ProvinciaServicio;

}
