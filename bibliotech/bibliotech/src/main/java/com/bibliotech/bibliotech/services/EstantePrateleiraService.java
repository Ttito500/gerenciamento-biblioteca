package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.response.ExemplarResponseDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.ExemplarResponseMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Exemplar;
import com.bibliotech.bibliotech.repositories.EstantePrateleiraRepository;
import com.bibliotech.bibliotech.repositories.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstantePrateleiraService {

    @Autowired
    private EstantePrateleiraRepository estantePrateleiraRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private ExemplarResponseMapper exemplarResponseMapper;

    public Estanteprateleira adicionarEstanteprateleira(Estanteprateleira ep) {

        if(ep.getPrateleira() == null){
            throw new ValidationException("A prateleira não pode ser vazia.");
        }
        if(ep.getEstante() == null || ep.getEstante().trim().isEmpty()){
            throw new ValidationException("A estante não pode ser vazia.");
        }
        if(ep.getEstante().length() > 1){
            throw new ValidationException("A estante só pode conter um caractere.");
        }

        ep.setEstante(ep.getEstante().trim().toUpperCase());
        if (estantePrateleiraRepository.findByEstanteAndPrateleira(ep.getEstante(), ep.getPrateleira()).isPresent()){
            throw new ValidationException("Já existe uma Estante-Prateleira com esses valores.");
        }

        return estantePrateleiraRepository.save(ep);
    }

    public List<Estanteprateleira> listarEstanteprateleiras() {
        return estantePrateleiraRepository.findAll();
    }

    public List<ExemplarResponseDTO> listarExemplaresPorEstantePrateleira(Integer idEstantePrateleira) {
        Estanteprateleira estanteprateleira = estantePrateleiraRepository.findById(idEstantePrateleira)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + idEstantePrateleira + " não encontrada."));
        List<Exemplar> exemplares = exemplarRepository.findByEstanteprateleira(estanteprateleira);
        if (exemplares.isEmpty()) {
            throw new NotFoundException("Nenhum exemplar encontrado para a Estante-Prateleira com ID " + idEstantePrateleira);
        }
        return exemplares.stream().map(exemplarResponseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String atualizarEstanteprateleira(Integer id, Estanteprateleira ep) {
        Estanteprateleira ep_existente = estantePrateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + id + " não encontrado."));

        if (ep.getEstante() == null || ep.getEstante().isEmpty()) {
            throw new ValidationException("A estante não pode ser vazia.");
        }
        if (ep.getPrateleira() == null) {
            throw new ValidationException("A prateleira não pode ser vazia.");
        }
        if (ep.getEstante().length() > 1) {
            throw new ValidationException("A estante só pode conter um caractere.");
        }

        String novaEstante = ep.getEstante().trim().toUpperCase();
        Integer novaPrateleira = ep.getPrateleira();

        if (estantePrateleiraRepository.findByEstanteAndPrateleira(novaEstante, novaPrateleira).isPresent()
                && !estantePrateleiraRepository.findByEstanteAndPrateleira(novaEstante, novaPrateleira).get().getId().equals(id)) {
            throw new ValidationException("Já existe uma Estante-Prateleira com esses valores.");
        }

        boolean estanteAtualizada = !ep_existente.getEstante().equals(novaEstante);
        boolean prateleiraAtualizada = !ep_existente.getPrateleira().equals(novaPrateleira);

        if (estanteAtualizada) {
            ep_existente.setEstante(novaEstante);
        }
        if (prateleiraAtualizada) {
            ep_existente.setPrateleira(novaPrateleira);
        }

        if (estanteAtualizada || prateleiraAtualizada) {
            estantePrateleiraRepository.save(ep_existente);
            if (estanteAtualizada && prateleiraAtualizada) {
                return "Estante e Prateleira atualizadas com sucesso.";
            } else if (estanteAtualizada) {
                return "Estante atualizada com sucesso.";
            } else {
                return "Prateleira atualizada com sucesso.";
            }
        }

        return "Nada foi atualizado.";
    }


    public String deletarEstanteprateleira (Integer id) {
        Estanteprateleira estanteprateleira = estantePrateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + id + " não encontrada."));

        if (exemplarRepository.existsByEstanteprateleira(estantePrateleiraRepository.findById(id).get())){
            throw new ValidationException("Não é possível deletar a estante-prateleira porque há exemplares associados a ela.");
        }

        estantePrateleiraRepository.delete(estanteprateleira);

        return "Estante-Prateleira deletada com sucesso";
    }

    public Estanteprateleira getEstantePrateleiraById(Integer id) {
        return estantePrateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com id: " + id + " não encontrada."));
    }
}