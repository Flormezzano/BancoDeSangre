package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import com.BancoDeSangre1.BancoDeSangre1.Enums.Roles;
import com.BancoDeSangre1.BancoDeSangre1.Enums.Sexo;
import com.BancoDeSangre1.BancoDeSangre1.Repositorios.Filtro;
import com.BancoDeSangre1.BancoDeSangre1.exception.ExceptionService;
import com.BancoDeSangre1.BancoDeSangre1.Repositorios.PersonaRepositorio;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Ciudad;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Persona;
import com.BancoDeSangre1.BancoDeSangre1.entidades.Provincia;
import com.BancoDeSangre1.BancoDeSangre1.entidades.TipoDeSangre;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    private Filtro filtro;

    public Persona Registro(Persona persona) throws ExceptionService {
        validacion(persona);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //LO Puse general para que Sprint lo trabaje y pueda usarlo en los dos metodos
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

        if (validacionPassword(persona)) {
            if (persona.getContrasenia1().equals(persona.getContrasenia2())) {
                persona.setContrasenia1(encoder.encode(persona.getContrasenia1()));
                persona.setContrasenia2(encoder.encode(persona.getContrasenia2()));
            } else {
                throw new ExceptionService("Las contraseñas no coinciden");
            }
        } else {
            throw new ExceptionService("La contraseña tiene que tener al menos 6 caracteres");
        }

        TipoDeSangre tipoSangre = tipoSangreServise.traerPorID(persona.getTipo().getId());
        persona.setTipo(tipoSangre);
        Provincia provincia = provinciaServise.traerPorID(persona.getProvincia().getId());
        persona.setProvincia(provincia);
        Ciudad ciudad = ciudadServise.traerPorID(persona.getCiudad().getId());
        persona.setCiudad(ciudad);
        if (validacioDate(persona) == true) {
            persona.setDonante(persona.getDonante());
        } else {
            persona.setDonante(false);
        }
        persona.setAlta(true);
        persona.setRol(Roles.USER);
        personaRepositorio.save(persona);
        return persona;
    }

    @Transactional
    public Persona modificar(Persona persona) throws ExceptionService {
        return validarModificaciones(persona);

    }

    private Persona validarModificaciones(Persona persona) throws ExceptionService {
        Optional<Persona> respuesta = personaRepositorio.findById(persona.getId());
        Persona personaVieja = persona;
        if (respuesta.isPresent()) {
            personaVieja = respuesta.get();
            if (persona.getNombre() != null && !personaVieja.getNombre().equals(persona.getNombre())) {
                personaVieja.setNombre(persona.getNombre());
            }

            if (persona.getApellido() != null && !personaVieja.getApellido().equals(persona.getApellido())) {
                personaVieja.setApellido(persona.getApellido());
            }
            if (persona.getEdad() != null && !personaVieja.getEdad().equals(persona.getEdad())) {
                personaVieja.setEdad(persona.getEdad());
            }
            if (persona.getDate() != null && !personaVieja.getDate().equals(persona.getDate())) {
                personaVieja.setDate(persona.getDate());
            }
            if (persona.getSexo() != null && !personaVieja.getSexo().equals(persona.getSexo())) {
                personaVieja.setSexo(persona.getSexo());
            }
            if (persona.getTipo() != null && !personaVieja.getTipo().equals(persona.getTipo())) {
                TipoDeSangre tipoSangre = tipoSangreServise.traerPorID(persona.getTipo().getId());
                personaVieja.setTipo(tipoSangre);
            }
            if (persona.getProvincia() != null && !personaVieja.getProvincia().equals(persona.getProvincia())) {
                Provincia provincia = provinciaServise.traerPorID(persona.getProvincia().getId());
                personaVieja.setProvincia(provincia);
            }
            if (persona.getCiudad() != null && !personaVieja.getCiudad().equals(persona.getCiudad())) {
                Ciudad ciudad = ciudadServise.traerPorID(persona.getCiudad().getId());
                personaVieja.setCiudad(ciudad);
            }
            if (validacioDate(persona) == true) {
                personaVieja.setDonante(persona.getDonante());
            } else {
                personaVieja.setDonante(false);
            }

            personaVieja.setAlta(personaVieja.getAlta());
            personaVieja.setRol(personaVieja.getRol());
            personaVieja.setMail(personaVieja.getMail());
            personaVieja.setContrasenia1(personaVieja.getContrasenia1());
            personaVieja.setContrasenia2(personaVieja.getContrasenia2());
            personaRepositorio.save(personaVieja);
        }
        return personaVieja;
    }

    public void baja(String id) {
        Persona persona;
        Optional<Persona> respuesta = personaRepositorio.findById(id);
        if (respuesta.isPresent()) {    
           persona=respuesta.get();
           persona.setAlta(false);
           personaRepositorio.save(persona);
        }
    }

    public Persona cambiarContrasenia(String id,String password, String confirmPassword) throws ExceptionService{
        System.out.println("aca entra");
        Persona persona = personaRepositorio.getById(id);
        
        passwordCheck(password, confirmPassword);
        recuperarContrasenia(persona, password);
        return persona;
    }
    
     public void passwordCheck(String password, String confirmPassword) throws ExceptionService {
         
        if (password == null || password.isEmpty() || password.length() < 6) {
            throw new ExceptionService("La clave esta vacia o tiene menos de 6 caracteres");
        }
        if (!password.equals(confirmPassword)) {
            throw new ExceptionService("Las contraseñas no coiciden");
        }
    }

    
     public void recuperarContrasenia(Persona persona, String password) throws ExceptionService {
         String encrypt = new BCryptPasswordEncoder().encode(password);
         persona.setContrasenia1(encrypt);
         persona.setContrasenia2(encrypt);
         personaRepositorio.save(persona);
    }

    public Persona buscarPorMail(String mail) {
        Persona persona = personaRepositorio.mail(mail);
        return persona;
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

    public Persona personaPorId(String id) {
        return personaRepositorio.getById(id);
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

        if (persona.getProvincia() == null) {
            throw new ExceptionService("Este campo de 'Provincia' no puede estar vacio");
        }
        if (persona.getCiudad() == null) {
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

    private Boolean validacionPassword(Persona persona) {
        Boolean aprobada = false;
        if (persona.getContrasenia1().length() >= 6) {
            aprobada = true;
        }
        return aprobada;
    }

    private Boolean validacioDate(Persona persona) throws ExceptionService {
        Boolean donante = false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(persona.getDate(), fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);

        if (periodo.getYears() > 18 && periodo.getYears() < 65) {
            donante = true;
        }
        return donante;
    }

     public List<Persona> filtrar (String provincia, String ciudad, String tipodesangre){
        return filtro.filtro(provincia,ciudad,tipodesangre);
    }

    public List<Sexo> sexo() {
        List<Sexo> sexos = Arrays.asList(Sexo.values());
        return sexos;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException { // VERIFICAR SI ESTA BIEN TERMINADO
        try {
            Persona persona = personaRepositorio.mail(mail);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + persona.getRol()));
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("personasession", persona);
            User user = new User(persona.getMail(), persona.getContrasenia1(), authorities);
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("El mail no existe");
        }
    }


}
