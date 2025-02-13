package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.dtos.response.AlunoLeiturasDTO;
import com.bibliotech.bibliotech.dtos.response.AlunoResponseDTO;
import com.bibliotech.bibliotech.models.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer>, JpaSpecificationExecutor<Emprestimo> {
    Page<Emprestimo> findByAlunoId(Integer idAluno, Pageable pageable);
    Page<Emprestimo> findByAlunoIdAndDataEmprestimo(Integer idAluno, LocalDate dataEmprestimo, Pageable pageable);

    Page<Emprestimo> findByExemplar_LivroId(Integer idExemplar, Pageable pageable);
    Page<Emprestimo> findByExemplar_LivroIdAndDataEmprestimo(Integer idExemplar, LocalDate dataEmprestimo, Pageable pageable);
}