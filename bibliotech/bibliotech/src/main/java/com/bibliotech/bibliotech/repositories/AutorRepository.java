package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findFirstByNomeIgnoreCase(String nome);

}
