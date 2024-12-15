package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Secao;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivrosService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro cadastrarLivro(Livro livro){
        livroRepository.save(livro);
        return livro;
    }

}
