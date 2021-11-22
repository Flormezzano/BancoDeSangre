package com.BancoDeSangre1.BancoDeSangre1.Repositorios;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
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
public interface PersonaRepositorio extends JpaRepository<Persona, String>{
    
    @Query("select p from Persona p WHERE p.alta = '1' AND p.donante = '1'")
    public List<Persona> listaPersona();
    
    @Query("select p from Persona p where p.provincia.nombre like :nombre")
    public List<Persona> listaPersonaPorProvincia(@Param("nombre") String nombre);
    
    @Query("select p from Persona p where p.ciudad.nombre like :nombre")
    public List<Persona> listaPersonaPorCiudad(@Param("nombre") String nombre);
    
    @Query("select p from Persona p where p.tipo.nombre like :nombre")
    public List<Persona> listaPersonaPorSangre(@Param("nombre") String nombre);
    
    @Query("select p from Persona p where p.mail like :nombre")
    public Persona mail(@Param("nombre") String nombre);
}
