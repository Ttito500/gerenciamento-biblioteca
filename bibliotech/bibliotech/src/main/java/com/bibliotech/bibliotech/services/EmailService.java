package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.EmailRequestDTO;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(EmailRequestDTO emailRequestDTO) {

        if(EmailValidator.isValid(emailRequestDTO.getTo())){
            throw new ValidationException("O e-mail informado não é válido.");
        }

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