package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    List<Genero> findByGeneroContainingIgnoreCase(String genero);
}
