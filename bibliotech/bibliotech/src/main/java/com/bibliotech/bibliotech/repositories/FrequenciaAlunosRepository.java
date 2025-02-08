package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.FrequenciaAlunos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FrequenciaAlunosRepository extends JpaRepository<FrequenciaAlunos, Integer> {
    @Query("SELECT f FROM FrequenciaAlunos f WHERE f.data = :data")
    List<FrequenciaAlunos> filtrarFrequencias(@Param("data") LocalDate data);
}
