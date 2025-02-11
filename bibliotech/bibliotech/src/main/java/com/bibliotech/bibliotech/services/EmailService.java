package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(EmailRequestDTO emailRequestDTO) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailRequestDTO.getTo());
            message.setSubject(emailRequestDTO.getSubject());
            message.setText(emailRequestDTO.getText());
            mailSender.send(message);

            return "E-mail enviado com sucesso!";
        } catch (Exception e) {

            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}