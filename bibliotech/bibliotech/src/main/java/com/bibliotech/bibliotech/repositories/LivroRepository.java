package com.bibliotech.bibliotech.repositories;

import com.bibliotech.bibliotech.models.Livro;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Livro findLivroById(Integer id);
    boolean existsLivroByIsbn(String isbn);

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Exemplar e WHERE e.id = :id AND e.situacao = 'emprestado') THEN true ELSE false END")
    boolean existsExemplarEmprestado(@Param("id") Integer id);

    @Query("SELECT COALESCE(MAX(e.numero), 0) FROM Exemplar e WHERE e.livro.id = :idLivro AND e.situacao = 'emprestado'")
    int findMaxNumeroExemplarEmprestado(@Param("idLivro") Integer idLivro);

    @Query("SELECT l FROM Livro l " +
            "LEFT JOIN Livroautor la ON l.id = la.livro.id " +
            "LEFT JOIN Autor a ON la.autor.id = a.id " +
            "LEFT JOIN Livrogenero lg ON l.id = lg.livro.id " +
            "LEFT JOIN Genero g ON lg.genero.id = g.id " +
            "WHERE (:titulo is null or l.titulo ILIKE %:titulo%) " +
            "AND (:isbn is null or l.isbn = :isbn) " +
            "AND (:autor is null or a.nome ILIKE %:autor%) " +
            "AND (:genero is null or g.genero ILIKE %:genero%) " +
            "AND (:ativo IS NULL OR l.ativo = :ativo)")
    Page<Livro> filtrarLivros(
            @Param("titulo") @Nullable String titulo,
            @Param("isbn") @Nullable String isbn,
            @Param("autor") @Nullable String autor,
            @Param("genero") @Nullable String genero,
            @Param("ativo") @Nullable Boolean ativo,
            Pageable pageable); // Adicione o Pageable

    Integer id(Integer id);
}
