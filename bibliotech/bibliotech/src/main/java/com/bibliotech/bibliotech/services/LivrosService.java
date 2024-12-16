package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro cadastrarLivro(Livro livro){
        
        livroRepository.save(livro);
        return livro;
    }

    public List<Livro> getLivros(){return livroRepository.findAll();}

    public Optional<Livro> getLivroById(Integer id){return livroRepository.findById(id);}
}
