package com.BancoDeSangre1.BancoDeSangre1.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Gastón
 */
@Entity
public class Ciudad implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    @ManyToOne
    private Provincia provincia;

    public Ciudad() {
    }

    public Ciudad(String id, String nombre, Provincia provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }
//Vamo los pibe
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    

}
