package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.mappers.AutorMapper;
import com.bibliotech.bibliotech.dtos.mappers.GeneroMapper;
import com.bibliotech.bibliotech.dtos.request.LivroRequestPostDTO;
import com.bibliotech.bibliotech.dtos.request.mappers.LivroRequestPostMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Secao;
import com.bibliotech.bibliotech.repositories.EstanteprateleiraRepository;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import com.bibliotech.bibliotech.repositories.SecaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivrosService {

    private AutorMapper autorMapper;
    private GeneroMapper generoMapper;

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
    @Autowired
    private LivroRequestPostMapper livroRequestPostMapper;

    @Transactional
    public Livro cadastrarLivro(LivroRequestPostDTO livro){
        Secao secaoExistente = secaoRepository.findById(livro.getIdSecao())
                .orElseThrow(() -> new NotFoundException("Seção não encontrada."));

        Estanteprateleira estanteprateleiraExistente = estanteprateleiraRepository.findById(livro.getIdEstanteprateleira())
                .orElseThrow(() -> new NotFoundException("Estante-Pratelerira não encontrada."));

        if (livroRepository.existsLivroByIsbn(livro.getIsbn())) {
            throw new ValidationException("Já existe um livro com esse isbn: " + livro.getIsbn());
        }
        if (livro.getIsbn().length() > 13) {
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

        Livro livroSalvo = livroRepository.save(livroRequestPostMapper.toEntity(livro));
        livroSalvo.setGeneros(generosService.addGenero(generoMapper.toEntityList(livro.getGeneros()), livroSalvo));
        livroSalvo.setAutores(autorService.cadastrarAutores(autorMapper.toEntityList(livro.getAutores()), livroSalvo));
        livroSalvo.setExemplares(exemplaresService.cadastrarExemplares(livroSalvo, secaoExistente, estanteprateleiraExistente, livro.getQtdExemplares()));

        return livroSalvo;
    }

    public List<Livro> getLivros(String titulo, String isbn, String autor, String generos, Boolean ativo){
        List<Livro> livrosSalvos = livroRepository.filtrarLivros(titulo, isbn, autor, generos, ativo);

        for (int i = 0; i < livrosSalvos.size(); i++) {
            livrosSalvos.get(i).setGeneros(generosService.findGenerosByLivroId(livrosSalvos.get(i).getId()));
            livrosSalvos.get(i).setAutores(autorService.findAutorByLivroId(livrosSalvos.get(i).getId()));
        }

        return livrosSalvos;
    }

    public Livro getLivroById(Integer id){
        Livro livroSalvo = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro com ID " + id + " não encontrado."));
        livroSalvo.setGeneros(generosService.findGenerosByLivroId(id));
        livroSalvo.setAutores(autorService.findAutorByLivroId(id));

        return livroSalvo;
    }

    public Livro atualizarLivro(Integer id, Livro livro){
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro com ID " + id + " não encontrado."));

        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setTitulo(livro.getTitulo());

        livroRepository.save(livroExistente);
        return livroExistente;
    }

    public void inativarLivro(Integer id) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro com ID: "+ id +" não encontrado."));

        if (livroRepository.existsExemplarEmprestado(id)) {
            throw new ValidationException("O livro não pode ser emprestado pois possui exemplares emprestados");
        }

        livroExistente.setAtivo(false);
        livroRepository.save(livroExistente);
    }
}