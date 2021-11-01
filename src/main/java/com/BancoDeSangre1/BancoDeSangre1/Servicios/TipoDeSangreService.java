/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import com.BancoDeSangre1.BancoDeSangre1.Repositorios.TipoDeSangreRepositorio;
import com.BancoDeSangre1.BancoDeSangre1.entidades.TipoDeSangre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Fraile
 */
@Service
public class TipoDeSangreService {
     @Autowired
    TipoDeSangreRepositorio TipoDeSangreRepositorio; 
     
     public List<TipoDeSangre> listaTipoSangre(){
        return TipoDeSangreRepositorio.listaTipoSangre();
    }
    
   
    
    
}
