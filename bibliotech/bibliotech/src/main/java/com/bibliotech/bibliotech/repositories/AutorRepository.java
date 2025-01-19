package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

}
