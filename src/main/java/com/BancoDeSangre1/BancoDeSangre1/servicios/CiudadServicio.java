package com.BancoDeSangre1.BancoDeSangre1.servicios;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Ciudad;
import com.BancoDeSangre1.BancoDeSangre1.repositorios.CiudadRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gastón
 */
@Service
public class CiudadServicio {
    
    @Autowired
    private CiudadRepositorio ciudadReposiotrio;
    
    public List<Ciudad> listaCiudad(){
        return ciudadReposiotrio.listaCiudad();
    }
    
    public List<Ciudad> listaCiudadPorNombre(String nombre){
        return ciudadReposiotrio.listaCiudadPorNombre("%"+nombre+"%");
    }
    
    public List<Ciudad> listaProvinciaPorNombre(String nombre){
        return ciudadReposiotrio.listaProvinciaPorNombre("%"+nombre+"%");
    }
}
