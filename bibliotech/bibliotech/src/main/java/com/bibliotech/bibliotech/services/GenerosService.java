package com.bibliotech.bibliotech.services;
import com.bibliotech.bibliotech.models.Genero;
import com.bibliotech.bibliotech.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerosService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> listGeneros() {
        return generoRepository.findAll();
    }
}
