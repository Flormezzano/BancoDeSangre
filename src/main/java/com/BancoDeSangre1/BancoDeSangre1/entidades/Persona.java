package com.BancoDeSangre1.BancoDeSangre1.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gastón
 */
@Entity
public class Persona implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String sexo;
    private String mail;
    @ManyToOne
    private TipoDeSangre tipo;
    @ManyToOne
    private Provincia provincia;
    @ManyToOne
    private Ciudad ciudad;
    private Boolean donante;

    public Persona() {
    }

    public Persona(String id, String nombre, String apellido, Integer edad, String sexo, String mail, TipoDeSangre tipo, Provincia provincia, Ciudad ciudad, Boolean donante) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.mail = mail;
        this.tipo = tipo;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.donante = donante;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TipoDeSangre getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeSangre tipo) {
        this.tipo = tipo;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getDonante() {
        return donante;
    }

    public void setDonante(Boolean donante) {
        this.donante = donante;
    }

    
}
