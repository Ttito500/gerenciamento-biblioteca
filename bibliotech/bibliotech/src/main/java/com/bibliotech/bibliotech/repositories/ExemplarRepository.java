package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
}
