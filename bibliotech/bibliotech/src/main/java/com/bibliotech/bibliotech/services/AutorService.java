package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor addAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor getAll() {
        return autorRepository.findAll();
    }
}

