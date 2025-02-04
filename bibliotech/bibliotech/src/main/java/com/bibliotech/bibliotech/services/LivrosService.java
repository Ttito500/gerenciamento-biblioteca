package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.AutorDTO;
import com.bibliotech.bibliotech.dtos.GeneroDTO;
import com.bibliotech.bibliotech.dtos.mappers.AutorMapper;
import com.bibliotech.bibliotech.dtos.mappers.GeneroMapper;
import com.bibliotech.bibliotech.dtos.request.LivroRequestDTO;
import com.bibliotech.bibliotech.dtos.response.EstanteprateleiraResponseDTO;
import com.bibliotech.bibliotech.dtos.response.ExemplarResponseDTO;
import com.bibliotech.bibliotech.dtos.response.LivroResponseDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.ExemplarResponseMapper;
import com.bibliotech.bibliotech.dtos.response.mappers.LivroResponseMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.*;
import com.bibliotech.bibliotech.repositories.AutorRepository;
import com.bibliotech.bibliotech.repositories.EstanteprateleiraRepository;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import com.bibliotech.bibliotech.repositories.SecaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivrosService {

    private AutorMapper autorMapper;
    private GeneroMapper generoMapper;
    private LivroResponseMapper livroResponseMapper;
    private ExemplarResponseMapper exemplarResponseMapper;

    @Autowired
    private AutorService autorService;
    @Autowired
    private GenerosService generosService;
    @Autowired
    private SecaoRepository secaoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private ExemplaresService exemplaresService;
    @Autowired
    private EstanteprateleiraRepository estanteprateleiraRepository;

    @Transactional
    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livro){
        Secao secaoExistente = secaoRepository.findById(livro.getIdSecao()).orElseThrow(() -> new NotFoundException("Seção não encontrada."));

        Estanteprateleira estanteprateleiraExistente = estanteprateleiraRepository.findById(livro.getIdEstanteprateleira()).orElseThrow(() -> new NotFoundException("Estante-Pratelerira não encontrada."));

        if (livroRepository.existsLivroByIsbn(livro.getIsbn())) {
            throw new ValidationException("Já existe um livro com esse isbn: " + livro.getIsbn());
        }
        if (livro.getIsbn().length() < 13) {
            throw new ValidationException("O tamanho máximo para ISBN é 13 caracteres.");
        }
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
           throw new ValidationException("O titulo do livro não pode ser nulo ou vazio.");
        }
        if (livro.getIsbn() == null || livro.getIsbn().trim().isEmpty()) {
           throw new ValidationException("O isbn do livro não pode ser nulo ou vazio.");
        }
        if (livro.getQtdExemplares() <= 0) {
           throw new ValidationException("A quantidade de exemplares não pode ser menor que ou igual à zero.");
        }
        if (livro.getAutores().isEmpty()) {
           throw new ValidationException("Os nomes autores não podom ser vazios ou nulos.");
        }
        if (livro.getGeneros().isEmpty()) {
           throw new ValidationException("Os generos não podom ser vazios ou nulos.");
        }

        Livro livroSalvo = livroRepository.save(livroToEntity(livro));

        List<Genero> generosSalvos = generosService.addGenero(generoMapper.toEntityList(livro.getGeneros()), livroSalvo);

        List<Autor> autoresSalvos = autorService.cadastrarAutores(autorMapper.toEntityList(livro.getAutores()), livroSalvo);

        List<Exemplar> exemplaresSalvos = exemplaresService.cadastrarExemplares(livro);

        LivroResponseDTO livroResponseDTO;

        livroResponseDTO = livroResponseMapper.toDTO(livroSalvo, autorMapper.toDTOList(autoresSalvos),
                generoMapper.toDTOList(generosSalvos), exemplarResponseMapper.toDTOList(exemplaresSalvos));

        return livroResponseDTO;
    }

    private Livro livroToEntity(LivroRequestDTO livro){
        Livro livroEntity = new Livro();

        livroEntity.setTitulo(livro.getTitulo());
        livroEntity.setIsbn(livro.getIsbn());
        livroEntity.setAtivo(true);

        return livroEntity;
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

    public List<Livro> getLivros(){
        return livroRepository.findAll();
    }

    public Optional<Livro> getLivroById(Integer id){
        return livroRepository.findById(id);
    }
}