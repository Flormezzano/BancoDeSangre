
package com.BancoDeSangre1.BancoDeSangre1.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Agustín
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    
    public void enviar(String from,String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        
    }
}
