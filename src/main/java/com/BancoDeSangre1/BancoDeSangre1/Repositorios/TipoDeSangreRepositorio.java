/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BancoDeSangre1.BancoDeSangre1.Repositorios;

import com.BancoDeSangre1.BancoDeSangre1.entidades.TipoDeSangre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Fraile
 */
@Repository
public interface TipoDeSangreRepositorio extends  JpaRepository<TipoDeSangre, String>{
    
    
    @Query("Select t from TipoDeSangre t")
    public List<TipoDeSangre> listaTipoSangre();
    
    @Query("Select t from TipoDeSangre t where t.nombre like :nombre")
    public List<TipoDeSangre> listaCiudadPorNombre(@Param("nombre") String nombre);
}

