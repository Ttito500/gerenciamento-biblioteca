package com.bibliotech.bibliotech.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSend {

    private final JavaMailSender mailSender;

    public EmailSend(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);

            return "E-mail enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace(); // Adicione logs para depuração
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}