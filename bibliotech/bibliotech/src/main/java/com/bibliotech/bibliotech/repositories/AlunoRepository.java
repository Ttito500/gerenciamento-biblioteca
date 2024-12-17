package com.bibliotech.bibliotech.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.bibliotech.bibliotech.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a " +
            "WHERE (:serie IS NULL OR a.idTurma.serie = :serie) " +
            "AND (:turma IS NULL OR a.idTurma.turma = :turma) " +
            "AND (:nome IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
            "AND (:situacao IS NULL OR a.situacao = :situacao)")
    List<Aluno> filtrarAlunos(
            @Param("serie") Integer serie,
            @Param("turma") String turma,
            @Param("nome") String nome,
            @Param("situacao") String situacao
    );
}