package com.BancoDeSangre1.BancoDeSangre1.Repositorios;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Ciudad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gastón
 */
@Repository
public interface CiudadRepositorio1 extends JpaRepository<Ciudad, String>{
    
    @Query("Select c from Ciudad c")
    public List<Ciudad> listaCiudad();
    
    @Query("Select c from Ciudad c where c.nombre like :nombre")
    public List<Ciudad> listaCiudadPorNombre(@Param("nombre") String nombre);
    
    @Query("Select c from Ciudad c where c.provincia.id = :id")
    public List<Ciudad> listaCiudadPorProvincia(@Param("id") String nombre);
}