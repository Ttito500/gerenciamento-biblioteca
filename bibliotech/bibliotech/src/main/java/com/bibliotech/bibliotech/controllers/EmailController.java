package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.EmailRequestDTO;
import com.bibliotech.bibliotech.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) { this.emailService = emailService; }

    @PostMapping("")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
            return ResponseEntity.ok(emailService.sendEmail(emailRequestDTO));
    }
}
