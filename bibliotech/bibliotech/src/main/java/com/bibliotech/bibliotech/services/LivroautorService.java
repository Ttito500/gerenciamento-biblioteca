package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Livroautor;
import com.bibliotech.bibliotech.repositories.AutorRepository;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import com.bibliotech.bibliotech.repositories.LivroautorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroautorService {

    @Autowired
    private LivroautorRepository livroautorRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public Livroautor cadastrarLivroautor(Integer livroId, Integer autorId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado"));

        //AQUI QUEBRA GABRIEL

        Livroautor livroautor = new Livroautor();
        livroautor.setId_livro(livro);
        livroautor.setId_autor(autor);

        livroautorRepository.save(livroautor);
        return livroautor;
    }

    //Teste de rota
    public List<Livroautor> getAllLivrosAutores(){
        return livroautorRepository.findAll();
    }
}
