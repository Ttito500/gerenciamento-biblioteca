package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.repositories.AutorRepository;
import com.bibliotech.bibliotech.repositories.LivroautorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroautorRepository livroautorRepository;

    public Autor addAutor(String nome_autor) {

        if(nome_autor==null || nome_autor.isEmpty()){
            throw new ValidationException("O nome do autor não pode estar vazio.");
        }

        Optional<Autor> autorOptional = buscarPorNome(nome_autor);
        Autor autor;

        if(autorOptional.isPresent()){
            //autor já existe no banco de dados
            autor = autorOptional.get();
            System.out.println("autor já existe");
        }else{
            //cadastrar novo autor
            autor = new Autor();
            autor.setNome(nome_autor);
            autorRepository.save(autor);
            System.out.println("autor adicionado");
        }
        return autor;
    }

    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorNome(String nome) {
        return autorRepository.findFirstByNomeIgnoreCase(nome);
    }

    // é mais facil saber os autores que estão associados a um livro, sabendo quem são, podemos subtrair
    // do total de autores no banco de dados, descobrindo quais são os NÃo associados (gepeto q fez).
    public void deleteAutoresSemAssociacao() {

        //buscar todos os IDs de autores associados a pelo menos um livro
        List<Integer> idsAutoresAssociados = livroautorRepository.findAll()
                .stream()
                .map(la -> la.getId_autor().getId())
                .distinct()
                .collect(Collectors.toList());

        //busca todos os autores que não estão na lista de associados
        List<Autor> autoresSemAssociacao = autorRepository.findByIdNotIn(idsAutoresAssociados);

        //deletar os autores sem associação
        autorRepository.deleteAll(autoresSemAssociacao);
    }
}
