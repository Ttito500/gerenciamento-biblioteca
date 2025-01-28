package com.bibliotech.bibliotech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "serie", nullable = false)
    private Integer serie;

    @Column(name = "turma", nullable = false, length = 1)
    private String turma;

    @Column(name = "ano_de_entrada", nullable = false)
    private Integer anoDeEntrada;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}