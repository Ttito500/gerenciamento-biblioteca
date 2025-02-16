package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Exemplar;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Secao;
import com.bibliotech.bibliotech.repositories.EstantePrateleiraRepository;
import com.bibliotech.bibliotech.repositories.ExemplarRepository;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import com.bibliotech.bibliotech.repositories.SecaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaresService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private SecaoRepository secaoRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private EstantePrateleiraRepository estantePrateleiraRepository;

    @Transactional
    public List<Exemplar> cadastrarExemplares(Livro livro, Secao secao, Estanteprateleira estanteprateleira, Integer qtdExemplaresNovos) {
        Livro livroCadastrado = livroRepository.findLivroById(livro.getId());
        List<Exemplar> exemplaresSalvos = new ArrayList<>();

        int qtd_exemplares_ja_existente = livroCadastrado.getExemplares().size();
        int qtd_exemplares_final = qtd_exemplares_ja_existente + qtdExemplaresNovos;

        for (int i = qtd_exemplares_ja_existente; i < qtd_exemplares_final; i++) {
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

    public List<Exemplar> listarExemplaresDeUmLivro(Integer id) {
        return exemplarRepository.findExemplarByLivro_Id(id);
    }
}
