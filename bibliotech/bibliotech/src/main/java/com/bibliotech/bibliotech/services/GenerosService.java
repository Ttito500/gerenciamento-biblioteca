package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Genero;
import com.bibliotech.bibliotech.repositories.GeneroRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerosService {
    @Autowired
    private GeneroRepository generoRepository;

    public Genero criarGenero(Genero genero) {
        if (StringUtils.isBlank(genero.getGenero())) {
            throw new ValidationException("O nome do gênero não pode ser vazio ou nulo");
        }
        if (generoRepository.existsByGenero(genero.getGenero())) {
            throw new ValidationException("Já existe um gênero com o nome '" + genero.getGenero() + "'");
        }

        return generoRepository.save(genero);
    }

    public void deletarGenerosSemAssociacao() {
        List<Genero> generosSemAssociacao = generoRepository.findGenerosSemAssociacao();
        System.out.println("Deletando " + generosSemAssociacao.size() + " gêneros não associados.");

        generoRepository.deleteAll(generosSemAssociacao);
    }

    public Genero getGeneroById(Integer id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gênero com ID " + id + " não encontrado"));
    }

    public List<Genero> filtrarGenero(String genero) {
        return generoRepository.filtrarGeneros(genero);
    }


}
