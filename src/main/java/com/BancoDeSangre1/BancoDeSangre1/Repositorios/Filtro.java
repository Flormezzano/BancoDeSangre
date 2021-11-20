package com.BancoDeSangre1.BancoDeSangre1.Repositorios;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gastón
 */

@Component
public class Filtro {

    private static final String PROVINCIA = "provincia";
    private static final String CIUDAD = "ciudad";
    private static final String TIPODESANGRE = "tipodesangre";

    @PersistenceContext
    private EntityManager entityManager;

    public List<Persona> filtro(String provincia, String ciudad, String tipodesangre) {
        javax.persistence.Query consulta = entityManager.createQuery(consulta(provincia, ciudad, tipodesangre));

        if (filtrar(provincia)) {
            consulta.setParameter(PROVINCIA, provincia);
        }
        if (filtrar(ciudad)) {
            consulta.setParameter(CIUDAD, ciudad);
        }
        if (filtrar(tipodesangre)) {
            consulta.setParameter(TIPODESANGRE, tipodesangre);
        }    
        return consulta.getResultList();
    }

    private boolean filtrar(String parametro) {
        return parametro != null && !parametro.trim().isEmpty();
    }
    

    private String consulta(String provincia, String ciudad,String tipodesangre) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT p FROM Persona p WHERE char_length(nombre) > 0 AND p.alta = '1'");

        if (filtrar(provincia)) {
            consulta.append(" AND p.provincia.nombre = :provincia");
        }
        if (filtrar(ciudad)) {
            consulta.append(" AND p.ciudad.nombre = :ciudad");
        }
        if (filtrar(tipodesangre)) {
            consulta.append(" AND p.tipo.nombre = :tipodesangre");
        }
        return consulta.toString();
    }
}
