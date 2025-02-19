package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.mappers.AutorMapper;
import com.bibliotech.bibliotech.dtos.mappers.GeneroMapper;
import com.bibliotech.bibliotech.dtos.request.ExemplarRequestPatchDTO;
import com.bibliotech.bibliotech.dtos.request.ExemplarRequestPostDTO;
import com.bibliotech.bibliotech.dtos.request.LivroRequestPatchDTO;
import com.bibliotech.bibliotech.dtos.request.LivroRequestPostDTO;
import com.bibliotech.bibliotech.dtos.request.mappers.LivroRequestPatchMapper;
import com.bibliotech.bibliotech.dtos.request.mappers.LivroRequestPostMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Exemplar;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Secao;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private SecoesService secoesService;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private ExemplaresService exemplaresService;
    @Autowired
    private LivroRequestPostMapper livroRequestPostMapper;
    @Autowired
    private LivroRequestPatchMapper livroRequestPatchMapper;
    @Autowired
    private EstantePrateleiraService estantePrateleiraService;

    @Transactional
    public Livro cadastrarLivro(LivroRequestPostDTO livro){
        Secao secaoExistente = secoesService.getSecaoById(livro.getIdSecao());
        Estanteprateleira estanteprateleiraExistente = estantePrateleiraService.getEstantePrateleiraById(livro.getIdEstanteprateleira());

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

    public Page<Livro> getLivros(String titulo, String isbn, String autor, String genero, Boolean ativo, Pageable pageable){
        Page<Livro> livrosSalvos = livroRepository.filtrarLivros(titulo, isbn, autor, genero, ativo, pageable);

        for (Livro livro : livrosSalvos) {
            livro.setExemplares(listarExemplaresDeUmLivro(livro.getId()));
            livro.setGeneros(generosService.findGenerosByLivroId(livro.getId()));
            livro.setAutores(autorService.findAutorByLivroId(livro.getId()));
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

    public Livro atualizarLivro(Integer id, LivroRequestPatchDTO livroRequest){
        Livro livro = getLivroById(id);

        if (livroRequest.getIsbn() != null && !livroRequest.getIsbn().isEmpty()) {
            if (livroRequest.getIsbn().length() > 13) {
                throw new ValidationException("O tamanho máximo para ISBN é 13 caracteres.");
            }
            if (livroRepository.existsLivroByIsbn(livroRequest.getIsbn()) && !livroRequest.getIsbn().equals(livro.getIsbn())) {
                throw new ValidationException("Já existe um livro com esse ISBN: " + livroRequest.getIsbn());
            }
            livro.setIsbn(livroRequest.getIsbn());
        }
        if (livroRequest.getAutores().isEmpty() || livroRequest.getAutores().getFirst().getNome().isEmpty()) {
            throw new ValidationException("Os nomes autores não podom ser vazios ou nulos.");
        }
        if (livroRequest.getGeneros().isEmpty() || livroRequest.getGeneros().getFirst().getGenero().isEmpty()) {
            throw new ValidationException("Os generos não podom ser vazios ou nulos.");
        }
        if (livroRequest.getTitulo() != null && !livroRequest.getTitulo().isEmpty()) {
            livro.setTitulo(livroRequest.getTitulo());
        }

        livro.setAutores(autorService.cadastrarNovosAutores(autorMapper.toEntityList(livroRequest.getAutores()), livro));
        livro.setGeneros(generosService.cadastrarNovosGeneros(generoMapper.toEntityList(livroRequest.getGeneros()), livro));

        livroRepository.save(livro);

        return livro;
    }

    public void inativarLivro(Integer id) {
        Livro livroExistente = getLivroById(id);

        if (livroRepository.existsExemplarEmprestado(id)) {
            throw new ValidationException("O livro não pode ser emprestado pois possui exemplares emprestados");
        }

        livroExistente.setAtivo(false);
        livroRepository.save(livroExistente);
    }

    public void ativarLivro(Integer id) {
        Livro livroExistente = getLivroById(id);

        livroExistente.setAtivo(true);
        livroRepository.save(livroExistente);
    }

    public List<Exemplar> cadastrarExemplaresDeUmLivro(ExemplarRequestPostDTO exemplarRequestPostDTO) {
        Livro livroExistente = getLivroById(exemplarRequestPostDTO.getIdLivro());
        Secao secaoExistente = secoesService.getSecaoById(exemplarRequestPostDTO.getIdSecao());
        Estanteprateleira estanteprateleiraExistente = estantePrateleiraService.getEstantePrateleiraById(exemplarRequestPostDTO.getIdEstanteprateleira());

        return exemplaresService.cadastrarExemplares(livroExistente, secaoExistente, estanteprateleiraExistente, exemplarRequestPostDTO.getQtdExemplares());
    }

    public List<Exemplar> listarExemplaresDeUmLivro(Integer id) {
        return exemplaresService.listarExemplaresDeUmLivro(id);
    }

    public void extraviarExemplar(Integer id) {
        exemplaresService.extraviarExemplar(id);
    }

    public Exemplar atualizarExemplar(Integer id, ExemplarRequestPatchDTO exemplarDTO) {
        return exemplaresService.atualizarExemplar(id, exemplarDTO);
    }
}