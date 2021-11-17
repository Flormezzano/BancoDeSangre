package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import com.BancoDeSangre1.BancoDeSangre1.Repositorios.CiudadRepositorio1;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Ciudad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gastón
 */
@Service
public class CiudadService {
    
    @Autowired
    private CiudadRepositorio1 ciudadReposiotrio;
    
    public List<Ciudad> listaCiudad(){
        return ciudadReposiotrio.listaCiudad();
    }
    
    public List<Ciudad> listaCiudadPorNombre(String nombre){
        return ciudadReposiotrio.listaCiudadPorNombre("%"+nombre+"%");
    }
    
    public List<Ciudad> listaCiudadPorProvincia(String id){
        return ciudadReposiotrio.listaCiudadPorProvincia(id);
    }
    
    public List<Ciudad> listar(){
     List<Ciudad> ciudades = ciudadReposiotrio.findAll();
      return ciudades;
    }
    
    public Ciudad traerPorID(String id) {
        return ciudadReposiotrio.getById(id);
    }
}
