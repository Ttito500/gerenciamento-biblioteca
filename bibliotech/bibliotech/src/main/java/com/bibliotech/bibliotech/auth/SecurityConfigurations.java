package com.bibliotech.bibliotech.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()  //MUDAR ISSO, permitir apenas bilbiotecario
                        .requestMatchers(HttpMethod.GET, "/usuarios/filtrar").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").authenticated() //a permissao mais abranjente de um mesmo http method e mesmo root de endpoint tem que estar em baixo das menos abranjentes
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.PATCH, "/usuarios/{id}/inativar").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.PATCH, "/usuarios/{id}/ativar").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/livros").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.POST, "/livros/exemplares").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.GET, "/livros/filtrar").hasAnyRole("bibliotecario", "aluno_monitor")
                        .requestMatchers(HttpMethod.GET, "/livros/exemplares/{id}").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/livros/inativar/{id}").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.PATCH, "/livros/ativar/{id}").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.PATCH, "/livros/exemplares/extraviar/{id}").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.PATCH, "/livros/exemplares/atualizar/{id}").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.GET, "/livros/relatorio/export/pdf").hasRole("bibliotecario")
                        .requestMatchers(HttpMethod.GET, "/livros/relatorio/acervo/export/pdf").hasRole("bibliotecario")


                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
