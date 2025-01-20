package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Livroautor;
import com.bibliotech.bibliotech.repositories.LivroautorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroautorService {

    @Autowired
    private LivroautorRepository livroautorRepository;

    public Livroautor cadastrarLivroautor(Livroautor livroautor) {
        livroautorRepository.save(livroautor);
        return livroautor;
    }

    //Teste de rota
    public List<Livroautor> getAllLivrosAutores(){
        return livroautorRepository.findAll();
    }
}
