package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.models.Exemplar;
import com.bibliotech.bibliotech.models.Secao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
    boolean existsByEstanteprateleira(Estanteprateleira estanteprateleira);

    boolean existsBySecao(Secao secao);
}
