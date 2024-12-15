package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
