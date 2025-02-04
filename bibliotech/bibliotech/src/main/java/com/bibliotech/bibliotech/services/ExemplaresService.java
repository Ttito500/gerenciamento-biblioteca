package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.LivroRequestDTO;
import com.bibliotech.bibliotech.dtos.response.ExemplarResponseDTO;
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
    public List<Exemplar> cadastrarExemplares(LivroRequestDTO livro) {
        Livro livroCadastrado = livroRepository.findLivroByIsbn(livro.getIsbn()); // Assumindo que você tem um método findLivroByIsbn no seu repositório

        if (livroCadastrado == null) {
            throw new RuntimeException("Livro não encontrado com ISBN: " + livro.getIsbn());
        }

        Optional<Secao> secaoOptional = secaoRepository.findById(livro.getIdSecao());
        if (!secaoOptional.isPresent()) {
            throw new RuntimeException("Seção não encontrada com o ID: " + livro.getIdSecao());
        }
        Secao secao = secaoOptional.get();

        Optional<Estanteprateleira> estantePrateleiraOptional = estanteprateleiraRepository.findById(livro.getIdEstanteprateleira());
        if (!estantePrateleiraOptional.isPresent()) {
            throw new RuntimeException("Estante e Prateleira não encontradas com o ID: " + livro.getIdEstanteprateleira());
        }
        Estanteprateleira estantePrateleira = estantePrateleiraOptional.get();

        List<Exemplar> exemplaresSalvos = new ArrayList<>();

        for (int i = 0; i < livro.getQtdExemplares(); i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setLivro(livroCadastrado);
            exemplar.setSecao(secao);
            exemplar.setEstanteprateleira(estantePrateleira);
            exemplar.setNumero(i + 1);
            exemplarRepository.save(exemplar);
            exemplaresSalvos.add(exemplar);
        }

        return exemplaresSalvos;
    }
}
