package com.bibliotech.bibliotech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Getter
@Setter
@Entity
@Table(name = "livroautor")
public class Livroautor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro idLivro;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor idAutor;

}