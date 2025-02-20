package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstantePrateleiraRepository extends JpaRepository<Estanteprateleira, Integer> {
    Optional<Estanteprateleira> findByEstanteAndPrateleira(String estante, Integer prateleira);
}
