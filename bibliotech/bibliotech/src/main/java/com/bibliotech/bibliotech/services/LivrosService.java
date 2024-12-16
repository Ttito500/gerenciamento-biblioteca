package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Secao;
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

    public Livro deletarLivro(Integer id){
        Optional<Livro> livro = livroRepository.findById(id);

        if(livro.isEmpty()){
            throw new RuntimeException("Livro com ID" + id + " não encontrado.");
        }

        Livro livroDeletado = livro.get();
        livroRepository.delete(livroDeletado);
        return livroDeletado;
    }

    public Livro atualizarLivro(Integer id, Livro livro){

        Optional<Livro> livroExistente = livroRepository.findById(id);

        Livro livroAtualizado = livroExistente.get();

        livroAtualizado.setIsbn(livro.getIsbn());
        livroAtualizado.setTitulo(livro.getTitulo());
        livroAtualizado.setAutor(livro.getAutor());
        livroAtualizado.setSituacao(livro.getSituacao());
        livroAtualizado.setObservacao(livro.getObservacao());
        livroAtualizado.setIdSecao(livro.getIdSecao());
        livroAtualizado.setIdEstantePrateleira(livro.getIdEstantePrateleira());

        livroRepository.save(livroAtualizado);
        return livroAtualizado;
    }

    public boolean existeLivro(Integer id){ return livroRepository.existsById(id); }

    public List<Livro> getLivros(){ return livroRepository.findAll(); }

    public Optional<Livro> getLivroById(Integer id){return livroRepository.findById(id);}
}