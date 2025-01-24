package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.models.Livroautor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroautorRepository extends JpaRepository<Livroautor, Integer> {
}