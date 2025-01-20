package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor addAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorNome(String nome) {
        return autorRepository.findFirstByNomeIgnoreCase(nome);
    }

    public boolean autorExiste(Autor autor) {
        return autorRepository.existsById(autor.getId());
    }

    public void deleteAutor(Autor autor) {
        // fazer a logica
        autorRepository.delete(autor);
    }
}
