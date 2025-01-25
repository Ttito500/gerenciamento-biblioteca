package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.repositories.EstanteprateleiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstanteprateleiraService {

    @Autowired
    private EstanteprateleiraRepository estateprateleiraRepository;

    public Estanteprateleira adicionarEstanteprateleira(Estanteprateleira ep) {

        if(estanteprateleiraExiste(ep.getEstante(), ep.getPrateleira())){
            throw new ValidationException("Estante-Prateleira ja existe");
        }

        return estateprateleiraRepository.save(ep);
    }

    public List<Estanteprateleira> listarEstanteprateleiras() {
        return estateprateleiraRepository.findAll();
    }

    private Boolean estanteprateleiraExiste(String estante, Integer prateleira) {
        return estateprateleiraRepository.existsByEstanteAndPrateleira(estante, prateleira);
    }

    public Estanteprateleira atualizarEstanteprateleira(Integer id, Estanteprateleira ep) {

        Estanteprateleira ep_existente = estateprateleiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estante-Prateleira com ID " + id + " não encontrado."));


        if (ep.getEstante() == null || ep.getEstante().isEmpty()) {
            throw new ValidationException("O campo 'estante' não pode ser vazio ou nulo.");
        }

        if (ep.getPrateleira() == null) {
            throw new ValidationException("O campo 'prateleira' não pode ser nulo.");
        }

        ep_existente.setEstante(ep.getEstante());
        ep_existente.setPrateleira(ep.getPrateleira());

        return estateprateleiraRepository.save(ep_existente);
    }

}
