package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.repositories.EstanteprateleiraRepository;
import com.bibliotech.bibliotech.repositories.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstanteprateleiraService {

    @Autowired
    private EstanteprateleiraRepository estateprateleiraRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

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
        if(estateprateleiraRepository.existsByEstante(ep.getEstante().toUpperCase())){
            throw new ValidationException("Ja existe Estante-Prateleira com essa estante");
        }


        ep.setEstante(ep.getEstante().toUpperCase());

        return estateprateleiraRepository.save(ep);
    }

    public List<Estanteprateleira> listarEstanteprateleiras() {
        return estateprateleiraRepository.findAll();
    }

    public Estanteprateleira atualizarEstanteprateleira(Integer id, Estanteprateleira ep) {
        Estanteprateleira ep_existente = estateprateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + id + " não encontrado."));

        if(ep.getEstante() == null || ep.getEstante().isEmpty()){
            throw new ValidationException("A estante não pode ser vazia.");
        }
        if(ep.getEstante().length() > 1){
            throw new ValidationException("A estante só pode conter um caractere.");
        }

        if (!ep_existente.getEstante().equalsIgnoreCase(ep.getEstante())) {
            if(estateprateleiraRepository.existsByEstante(ep.getEstante())) {
                throw new ValidationException("Já existe Estante-Prateleira com essa estante.");
            }
        }

        ep_existente.setEstante(ep.getEstante().toUpperCase());
        ep_existente.setPrateleira(ep.getPrateleira());

        return estateprateleiraRepository.save(ep_existente);
    }

    public String deletarEstanteprateleira (Integer id) {
        Estanteprateleira estanteprateleira = estateprateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + id + " não encontrada."));

        if (exemplarRepository.existsByEstanteprateleira(estateprateleiraRepository.findById(id).get())){
            throw new ValidationException("Não é possível deletar a estante-prateleira porque há exemplares associados a ela.");
        }

        estateprateleiraRepository.delete(estanteprateleira);

        return "Estante-Prateleira deletada com sucesso";
    }

}
