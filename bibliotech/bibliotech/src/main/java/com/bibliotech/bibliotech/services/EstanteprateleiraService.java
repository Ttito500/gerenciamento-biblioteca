package com.bibliotech.bibliotech.services;

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

}
