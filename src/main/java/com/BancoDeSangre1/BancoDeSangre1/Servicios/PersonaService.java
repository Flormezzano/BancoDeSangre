package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import com.BancoDeSangre1.BancoDeSangre1.Enums.Roles;
import com.BancoDeSangre1.BancoDeSangre1.Enums.Sexo;
import com.BancoDeSangre1.BancoDeSangre1.exception.ExceptionService;
import com.BancoDeSangre1.BancoDeSangre1.Repositorios.PersonaRepositorio;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Ciudad;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Provincia;
import com.BancoDeSangre1.BancoDeSangre1.entidades.TipoDeSangre;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gastón
 */
@Service
public class PersonaService implements UserDetailsService {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private TipoDeSangreService tipoSangreServise;

    @Autowired
    private CiudadService ciudadServise;

    @Autowired
    private ProvinciaService provinciaServise;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Persona Registro(Persona persona) throws ExceptionService {
        validacion(persona);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //LO Puse general para que Sprint lo trabaje y pueda usarlo en los dos metodos
        persona.setNombre(persona.getNombre());
        persona.setApellido(persona.getApellido());
        persona.setEdad(calcularEdad(persona));

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String fechaString = persona.getDate();
//        LocalDate fechaNac = LocalDate.parse(fechaString, fmt);
//        ZoneId defaultZoneId = ZoneId.systemDefault();
//        Date fechaDate = Date.from(fechaNac.atStartOfDay(defaultZoneId).toInstant());
        persona.setDate(persona.getDate());
        persona.setSexo(persona.getSexo());

        Persona verificacionMail = personaRepositorio.mail(persona.getMail());
        if (verificacionMail == null) {
            persona.setMail(persona.getMail());
        } else {
            throw new ExceptionService("Lo sentimos, este mail está en uso");
        }

        if (persona.getContrasenia1().equals(persona.getContrasenia2())) {
            persona.setContrasenia1(encoder.encode(persona.getContrasenia1()));
            persona.setContrasenia2(encoder.encode(persona.getContrasenia2()));
        } else {
            throw new ExceptionService("Las contraseñas no coinciden");
        }

        TipoDeSangre tipoSangre = tipoSangreServise.traerPorID(persona.getTipo().getId());
        persona.setTipo(tipoSangre);
        Provincia provincia = provinciaServise.traerPorID(persona.getProvincia().getId());
        persona.setProvincia(provincia);
        Ciudad ciudad = ciudadServise.traerPorID(persona.getCiudad().getId());
        persona.setCiudad(ciudad);

        if (validacioDate(persona) == true) { //VER ESTO QUE NOS FALTA
            persona.setDonante(persona.getDonante());
        } else {
            persona.setDonante(false);
            System.out.println("Lo sentimos, no cumple con el requisito de edad para donar sangre");
        }
        persona.setAlta(true);
        persona.setRol(Roles.USER);
        personaRepositorio.save(persona);
        return persona;
    }

    public Persona modificar(Persona persona) throws ExceptionService {

        Optional<Persona> respuesta = personaRepositorio.findById(persona.getId());
        if (respuesta.isPresent()) {
            persona = respuesta.get();
            persona.setNombre(persona.getNombre());
            persona.setApellido(persona.getApellido());
            persona.setEdad(calcularEdad(persona));
            persona.setDate(persona.getDate());
            persona.setSexo(persona.getSexo());

            Persona verificacionMail = personaRepositorio.mail(persona.getMail());
            if (verificacionMail == null) {
                persona.setMail(persona.getMail());
            } else {
                throw new ExceptionService("Lo sentimos, este mail está en uso");
            }

            if (persona.getContrasenia1().equals(persona.getContrasenia2())) {
                persona.setContrasenia1(encoder.encode(persona.getContrasenia1()));
                persona.setContrasenia2(encoder.encode(persona.getContrasenia2()));
            } else {
                throw new ExceptionService("Las contraseñas no coinciden");
            }

            TipoDeSangre tipoSangre = tipoSangreServise.traerPorID(persona.getTipo().getId());
            persona.setTipo(tipoSangre);
            Provincia provincia = provinciaServise.traerPorID(persona.getProvincia().getId());
            persona.setProvincia(provincia);
            Ciudad ciudad = ciudadServise.traerPorID(persona.getCiudad().getId());
            persona.setCiudad(ciudad);

            if (validacioDate(persona) == true) { //VER ESTO QUE NOS FALTA
                persona.setDonante(persona.getDonante());
            } else {
                persona.setDonante(false);
                System.out.println("Lo sentimos, no cumple con el requisito de edad para donar sangre");
            }
           
            personaRepositorio.save(persona);
        } else {
            throw new ExceptionService("No se encuentra el usuario");
        }
        return persona;
    }

    public void baja(Persona persona) {
        Optional<Persona> respuesta = personaRepositorio.findById(persona.getId());
        if (respuesta.isPresent()) {
            persona = respuesta.get();
            persona.setAlta(false);
            personaRepositorio.save(persona);
        }
    }

    //VALIDACION MAIL - CONTROLLER LIA 89
//    public void iniciarSesion(Persona persona) throws ExceptionService {
//        Persona mail = personaRepositorio.mail(persona.getMail());
//        String contraseña = mail.getContrasenia1();
//        if (mail != null) {
//            if (!persona.getContrasenia1().equals(contraseña)) {
//                throw new ExceptionService("Contraseña invalida");
//            }
//        } else {
//            throw new ExceptionService("El mail ingresado no se encuentra registrado");
//        }
//    }
    
    public void recuperarContrasenia(Persona persona) throws ExceptionService {
        Persona mail = personaRepositorio.mail(persona.getMail());
        if (mail != null) {
            if (mail.getContrasenia1().equals(persona.getContrasenia2())) {
                mail.setContrasenia1(encoder.encode(persona.getContrasenia1()));
                mail.setContrasenia2(encoder.encode(persona.getContrasenia2()));
                personaRepositorio.save(mail);
            } else {
                throw new ExceptionService("Las contraseñas no coinciden");
            }
        } else {
            throw new ExceptionService("El mail ingresado no se encuentra registrado");
        }
    }

    public List<Persona> listaPersonaPorProvincia(String nombre) {
        return personaRepositorio.listaPersonaPorProvincia(nombre);
    }

    public List<Persona> listaPersonaPorCiudad(String nombre) {
        return personaRepositorio.listaPersonaPorCiudad(nombre);
    }

    public List<Persona> listaPersonaPorSangre(String nombre) {
        return personaRepositorio.listaPersonaPorSangre(nombre);
    }

    public List<Persona> listaPersona() {
        return personaRepositorio.findAll();
    }

    public Persona personaPorId(Persona persona) {
        return personaRepositorio.getById(persona.getId());
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
        if (persona.getSexo() == null) {
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
        if (persona.getTipo() == null) {
            throw new ExceptionService("Este campo de 'Tipo de Sangre' no puede estar nulo");
        }
        if (persona.getTipo().getNombre().isEmpty()) {
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

    private Integer calcularEdad(Persona persona) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(persona.getDate(), fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);

        return periodo.getYears();
    }

    private Boolean validacioDate(Persona persona) throws ExceptionService { //VER QUE TODAVIA NOS FALTA
        Boolean donante = false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaNac = LocalDate.parse("1999/06/02", fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);

        if (periodo.getYears() > 18 && periodo.getYears() < 65) {
            donante = true;
        } else {
            throw new ExceptionService("Lo sentimos, no cumple con el requisito de edad para donar sangre");
        }
        return donante;
    }
    
    public List<Sexo> sexo() {
        List<Sexo> sexos = Arrays.asList(Sexo.values());
        return sexos;
    }

    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException { // VERIFICAR SI ESTA BIEN TERMINADO
        try {
            Persona persona = personaRepositorio.mail(mail);
            UserBuilder user;
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLES_" + persona.getRol()));
            return new User(mail, persona.getContrasenia1(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El mail no existe");
        }
        
        //             if(persona != null){
        //                 user = User.withUsername(mail);
        //                 user.disabled(false);
        //                 user.password(persona.getContrasenia1());
        //                 user.authorities(new SimpleGrantedAuthority("ROL_USER"));
        //             }else{
        //                 throw new UsernameNotFoundException("Mail no existente");
        //             }
        //              return user.build();

    }

}
