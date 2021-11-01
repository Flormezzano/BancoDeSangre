/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BancoDeSangre1.BancoDeSangre1.controllers;

import com.BancoDeSangre1.BancoDeSangre1.Servicios.TipoDeSangreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Martin Fraile
 */
@Controller
@RequestMapping("")
public class TipoDeSangreController {

    TipoDeSangreService TipoDeSangreServicio;
}
