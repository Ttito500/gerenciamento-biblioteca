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
        if(estantePrateleiraRepository.existsByEstante(ep.getEstante().toUpperCase())){
            throw new ValidationException("Ja existe Estante-Prateleira com essa estante");
        }


        ep.setEstante(ep.getEstante().toUpperCase());

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
    public Estanteprateleira atualizarEstanteprateleira(Integer id, Estanteprateleira ep) {
        Estanteprateleira ep_existente = estantePrateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + id + " não encontrado."));

        if(ep.getEstante() == null || ep.getEstante().isEmpty()){
            throw new ValidationException("A estante não pode ser vazia.");
        }
        if(ep.getEstante().length() > 1){
            throw new ValidationException("A estante só pode conter um caractere.");
        }

        if (!ep_existente.getEstante().equalsIgnoreCase(ep.getEstante())) {
            if(estantePrateleiraRepository.existsByEstante(ep.getEstante())) {
                throw new ValidationException("Já existe Estante-Prateleira com essa estante.");
            }
        }

        ep_existente.setEstante(ep.getEstante().toUpperCase());
        ep_existente.setPrateleira(ep.getPrateleira());

        return estantePrateleiraRepository.save(ep_existente);
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