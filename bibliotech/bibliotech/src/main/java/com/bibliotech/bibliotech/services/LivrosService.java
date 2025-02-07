package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.LivroRequestDTO;
import com.bibliotech.bibliotech.dtos.request.mappers.LivroRequestMapper;
import com.bibliotech.bibliotech.dtos.response.LivroResponseDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.LivroResponceMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    @Autowired
    AutorService autorService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroRequestMapper livroRequestMapper;

    @Autowired
    private LivroResponceMapper livroResponceMapper;


    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livroRequestDTO) {
        
        Livro livro = livroRequestMapper.toEntity(livroRequestDTO);
        livro.setAtivo(true);

        // Autor autor = autorService.addAutor(livro.getAutor()); (refazer devido a lista de autores)

        // livroautorService.cadastrarLivroautor(livro.getId(), autor.getId()); (refazer devido a lista de autores)

        livroRepository.save(livro);
        return livroResponceMapper.toDto(livro);
    }

    public Livro deletarLivro(Integer id){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro com ID " + id + " não encontrado."));

        livroRepository.delete(livro);
        return livro;
    }

    public Livro atualizarLivro(Integer id, Livro livro){

        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro com ID " + id + " não encontrado."));

        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setTitulo(livro.getTitulo());

        livroRepository.save(livroExistente);
        return livroExistente;
    }

    public List<Livro> getLivros(){ return livroRepository.findAll(); }

    public Optional<Livro> getLivroById(Integer id){return livroRepository.findById(id);}
}