package com.BancoDeSangre1.BancoDeSangre1.entidades;

import com.BancoDeSangre1.BancoDeSangre1.Enums.Roles;
import com.BancoDeSangre1.BancoDeSangre1.Enums.Sexo;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Gastón
 */
@Entity
public class Persona implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String date;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String mail;
    private String contrasenia1;
    private String contrasenia2;
    @ManyToOne (cascade=CascadeType.PERSIST)
    private TipoDeSangre tipo;
    @ManyToOne (cascade=CascadeType.PERSIST)
    private Provincia provincia;
    @ManyToOne (cascade=CascadeType.PERSIST)
    private Ciudad ciudad;
    private Boolean donante;
    private Boolean alta;
    @Enumerated(EnumType.STRING)
     private Roles rol;

  

    public Persona() {
    }

    public Persona(String id, String nombre, String apellido, String date, Sexo sexo, String mail, String contrasenia1, String contrasenia2, TipoDeSangre tipo, Provincia provincia, Ciudad ciudad, Boolean donante, Boolean alta, Roles rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.date = date;
        this.sexo = sexo;
        this.mail = mail;
        this.contrasenia1 = contrasenia1;
        this.contrasenia2 = contrasenia2;
        this.tipo = tipo;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.donante = donante;
        this.alta = alta;
        this.rol = rol;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenia1() {
        return contrasenia1;
    }

    public void setContrasenia1(String contrasenia1) {
        this.contrasenia1 = contrasenia1;
    }

    public String getContrasenia2() {
        return contrasenia2;
    }

    public void setContrasenia2(String contrasenia2) {
        this.contrasenia2 = contrasenia2;
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

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
} 
