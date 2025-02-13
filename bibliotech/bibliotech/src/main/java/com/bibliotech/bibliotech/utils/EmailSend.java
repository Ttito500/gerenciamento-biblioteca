package com.bibliotech.bibliotech.utils;

import com.bibliotech.bibliotech.dtos.request.EmailRequestDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSend {

    private final JavaMailSender mailSender;

    public EmailSend(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendEmail(EmailRequestDTO emailRequestDTO) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailRequestDTO.getTo());
            message.setSubject(emailRequestDTO.getSubject());
            message.setText(emailRequestDTO.getText());
            mailSender.send(message);

            return "E-mail enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace(); // Adicione logs para depuração
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}