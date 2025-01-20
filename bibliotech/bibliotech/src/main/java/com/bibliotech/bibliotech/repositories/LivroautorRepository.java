package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Livroautor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroautorRepository extends JpaRepository<Livroautor, Integer> {
}