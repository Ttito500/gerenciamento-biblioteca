package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstantePrateleiraRepository extends JpaRepository<Estanteprateleira, Integer> {
    boolean existsByEstante(String estante);
}
