package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.LivroRequestPostDTO;
import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Exemplar;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Secao;
import com.bibliotech.bibliotech.repositories.EstanteprateleiraRepository;
import com.bibliotech.bibliotech.repositories.ExemplarRepository;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import com.bibliotech.bibliotech.repositories.SecaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExemplaresService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private SecaoRepository secaoRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private EstanteprateleiraRepository estanteprateleiraRepository;

    @Transactional
    public List<Exemplar> cadastrarExemplares(Livro livro, Secao secao, Estanteprateleira estanteprateleira, Integer qtdExemplares) {
        Livro livroCadastrado = livroRepository.findLivroById(livro.getId());
        List<Exemplar> exemplaresSalvos = new ArrayList<>();

        for (int i = 0; i < qtdExemplares; i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setLivro(livroCadastrado);
            exemplar.setSecao(secao);
            exemplar.setEstanteprateleira(estanteprateleira);
            exemplar.setNumero(i + 1);
            exemplarRepository.save(exemplar);
            exemplaresSalvos.add(exemplar);
        }

        return exemplaresSalvos;
    }
}
