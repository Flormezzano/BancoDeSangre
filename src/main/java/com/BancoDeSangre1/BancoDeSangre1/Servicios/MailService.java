package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Agustín
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    /**
     * se encarga de enviar los email a los usuarios. se marca como async para
     * que se haga en otro hilo de ejecucion de forma paralela y no hacer
     * esperar al usuario a que se complete.
     *
     * @param body
     * @param title
     * @param mail
     */
    @Async
    public void enviarMail(String body, String title, String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setFrom("factorvida2021@gmail.com");
        message.setSubject(title);
        message.setText(body);
        javaMailSender.send(message);
    }

}
