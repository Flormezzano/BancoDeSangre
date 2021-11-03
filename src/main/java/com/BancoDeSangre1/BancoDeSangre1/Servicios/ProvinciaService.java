
package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import com.BancoDeSangre1.BancoDeSangre1.Repositorios.ProvinciaRepo;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Provincia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProvinciaService {
    
    @Autowired
    ProvinciaRepo PciaRepo; 
    
    public List<Provincia> findByName(String name){
        List<Provincia>provincias=PciaRepo.findByName(name);
        return provincias;
    }
    
    public List<Provincia> listar(){
     List<Provincia> provincias = PciaRepo.findAll();
      return provincias;
}
}
