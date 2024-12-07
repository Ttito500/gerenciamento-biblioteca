package com.bibliotech.bibliotech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "genero", schema = "adelino_cunha")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "genero", nullable = false)
    private String genero;

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public Long getId() {
        return id;
    }
}