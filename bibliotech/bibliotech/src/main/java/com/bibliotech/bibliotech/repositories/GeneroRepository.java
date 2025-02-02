package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Genero;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    @Query("SELECT g FROM Genero g WHERE NOT EXISTS (SELECT lg FROM Livrogenero lg WHERE lg.genero = g)")
    List<Genero> findGenerosSemAssociacao();

    boolean existsByGenero(String genero);

    Optional<Genero> findByGenero(String genero);
}
