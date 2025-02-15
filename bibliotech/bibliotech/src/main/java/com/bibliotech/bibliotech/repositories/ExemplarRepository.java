package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
    List<Exemplar> findExemplarByLivro_Id(Integer id);
}
