package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.AutenticacaoDTO;
import com.bibliotech.bibliotech.dtos.request.UsuarioRequestDTO;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        var tokenAutenticacao = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getEmail(), autenticacaoDTO.getSenha());
        var autenticacao = authenticationManager.authenticate(tokenAutenticacao);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(UsuarioRequestDTO usuarioRequestDTO){
        if (this.usuarioRepository.findByEmail(usuarioRequestDTO.getEmail()) != null){
            throw new ValidationException("Esse email já está atrelado a um usuário");
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(usuarioRequestDTO.getSenha());

        return ResponseEntity.ok().build();
    }
}
