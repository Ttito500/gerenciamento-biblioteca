package com.bibliotech.bibliotech.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bibliotech.bibliotech.exception.TokenExeption;
import com.bibliotech.bibliotech.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("bibliotech")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algoritimo);
            return token;
        } catch (JWTCreationException exception){
            throw new TokenExeption("Erro ao criar token de autenticação");
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("bibliotech")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenExeption(exception.getMessage());
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00")); //horario de brasilia
    }
}
