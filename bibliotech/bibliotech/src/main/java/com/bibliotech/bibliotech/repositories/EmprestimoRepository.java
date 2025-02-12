package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>, JpaSpecificationExecutor<Emprestimo> {
    Page<Emprestimo> findByAlunoId(Integer idAluno, Pageable pageable);
    Page<Emprestimo> findByAlunoIdAndDataEmprestimo(Integer idAluno, LocalDate dataEmprestimo, Pageable pageable);

    Page<Emprestimo> findByExemplar_LivroId(Integer idExemplar, Pageable pageable);
    Page<Emprestimo> findByExemplar_LivroIdAndDataEmprestimo(Integer idExemplar, LocalDate dataEmprestimo, Pageable pageable);

    @Query("SELECT new com.bibliotech.bibliotech.dto.AlunoLeitorDTO(a.nome, a.turma, COUNT(e)) " +
            "FROM Emprestimo e JOIN e.aluno a " +
            "WHERE e.situacao = 'entregue' " +
            "GROUP BY a.nome, a.turma " +
            "ORDER BY COUNT(e) DESC")
    List<Aluno> findTopLeitores();
}