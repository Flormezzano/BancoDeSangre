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
    
<<<<<<< HEAD
    
    @Query("Select t from TipoDeSangre t")
=======
    @Query("Select t from TipoDeSangre t ")
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
    public List<TipoDeSangre> listaTipoSangre();
    
    @Query("Select t from TipoDeSangre t where t.nombre like :nombre")
    public List<TipoDeSangre> listaCiudadPorNombre(@Param("nombre") String nombre);
<<<<<<< HEAD

    
=======
>>>>>>> 63803b5b78ea83c9f6449af4720dffb54792fc88
}
