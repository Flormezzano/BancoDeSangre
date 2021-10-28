package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import com.BancoDeSangre1.BancoDeSangre1.exception.ExceptionService;
import com.BancoDeSangre1.BancoDeSangre1.Repositorios.PersonaRepositorio;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gastón
 */
@Service
public class PersonaService {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Persona Registro(Persona persona) throws ExceptionService {

        validacion(persona);
        persona.setNombre(persona.getNombre());
        persona.setApellido(persona.getApellido());
        persona.setDate(persona.getDate());
        persona.setSexo(persona.getSexo());

        Persona verificacionMail = personaRepositorio.mail(persona.getMail());
        if (verificacionMail == null) {
            persona.setMail(persona.getMail());
        } else {
            throw new ExceptionService("Lo sentimos, este mail está en uso");
        }

        persona.setContrasenia1(persona.getContrasenia1());
        if (persona.getContrasenia1() == persona.getContrasenia2()) {
            persona.setContrasenia2(persona.getContrasenia2());
        } else {
            throw new ExceptionService("Las contraseñas no coinciden");
        }

        persona.setTipo(persona.getTipo());
        persona.setProvincia(persona.getProvincia());
        persona.setCiudad(persona.getCiudad());
        persona.setDonante(persona.getDonante());
        persona.setAlta(true);

        personaRepositorio.save(persona);
        return persona;
    }

    public void modificar(Persona persona) throws ExceptionService {

        Optional<Persona> respuesta = personaRepositorio.findById(persona.getId());
        if (respuesta.isPresent()) {
            persona = respuesta.get();
            persona.setNombre(persona.getNombre());
            persona.setApellido(persona.getApellido());
            persona.setDate(persona.getDate());
            persona.setSexo(persona.getSexo());

            Persona verificacionMail = personaRepositorio.mail(persona.getMail());
            if (verificacionMail == null) {
                persona.setMail(persona.getMail());
            } else {
                throw new ExceptionService("Lo sentimos, este mail está en uso");
            }

            persona.setContrasenia1(persona.getContrasenia1());
            if (persona.getContrasenia1() == persona.getContrasenia2()) {
                persona.setContrasenia2(persona.getContrasenia2());
            } else {
                throw new ExceptionService("Las contraseñas no coinciden");
            }
            
            persona.setTipo(persona.getTipo());
            persona.setProvincia(persona.getProvincia());
            persona.setCiudad(persona.getCiudad());
            persona.setDonante(persona.getDonante());

            personaRepositorio.save(persona);
        } else{
            throw new ExceptionService("No se encuentra el usuario");
        }
    }
    
    public void baja(Persona persona){
        Optional<Persona> respuesta = personaRepositorio.findById(persona.getId());
        if(respuesta.isPresent()){
            persona = respuesta.get();
            persona.setAlta(false);
            personaRepositorio.save(persona);
        }
    }
    
    public List<Persona> listaPersonaPorProvincia(String nombre){
        return personaRepositorio.listaPersonaPorProvincia(nombre);
    }
    
    public List<Persona> listaPersonaPorCiudad(String nombre){
        return personaRepositorio.listaPersonaPorCiudad(nombre);
    }
    
    public List<Persona> listaPersonaPorSangre(String nombre){
        return personaRepositorio.listaPersonaPorSangre(nombre);
    }

    private void validacion(Persona persona) throws ExceptionService {
        if (persona.getNombre() == null || persona.getNombre().isEmpty()) {
            throw new ExceptionService("Este campo de 'Nombre' no puede estar vacio");
        }
        if (persona.getApellido() == null || persona.getApellido().isEmpty()) {
            throw new ExceptionService("Este campo de 'Apellido' no puede estar vacio");
        }
        if (persona.getDate() == null) {
            throw new ExceptionService("Este campo de 'Fecha de Nacimiento' no puede estar vacio");
        }
        if (persona.getSexo() == null || persona.getSexo().isEmpty()) {
            throw new ExceptionService("Este campo de 'Sexo' no puede estar vacio");
        }
        if (persona.getMail() == null || persona.getMail().isEmpty()) {
            throw new ExceptionService("Este campo de 'Mail' no puede estar vacio");
        }
        if (persona.getContrasenia1() == null || persona.getContrasenia1().isEmpty()) {
            throw new ExceptionService("Este campo de 'Contraseña' no puede estar vacio");
        }
        if (persona.getContrasenia2() == null || persona.getContrasenia2().isEmpty()) {
            throw new ExceptionService("Este campo de 'Contraseña' no puede estar vacio");
        }
        if (persona.getTipo() == null || persona.getTipo().getNombre().isEmpty()) {
            throw new ExceptionService("Este campo de 'Tipo de Sangre' no puede estar vacio");
        }
        if (persona.getProvincia() == null || persona.getProvincia().getNombre().isEmpty()) {
            throw new ExceptionService("Este campo de 'Provincia' no puede estar vacio");
        }
        if (persona.getCiudad() == null || persona.getCiudad().getNombre().isEmpty()) {
            throw new ExceptionService("Este campo de 'Ciudad' no puede estar vacio");
        }
        if (persona.getDonante() == null) {
            persona.setDonante(false);
        }
    }
}
