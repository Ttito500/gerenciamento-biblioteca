package com.bibliotech.bibliotech.dtos.request.mappers;

import com.bibliotech.bibliotech.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRequestMapper {
    @Autowired
    private UsuarioRepository usuarioRepository;


}
