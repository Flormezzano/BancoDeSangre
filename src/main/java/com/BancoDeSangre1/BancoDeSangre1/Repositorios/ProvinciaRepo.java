
package com.BancoDeSangre1.BancoDeSangre1.Repositorios;

import com.BancoDeSangre1.BancoDeSangre1.entidades.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepo extends JpaRepository<Provincia, String> {
    
    
    
    @Query("SELECT c FROM Provincia c WHERE c.nombre LIKE :name")
    public List<Provincia> findByName(@Param("name") String name);
    
}
