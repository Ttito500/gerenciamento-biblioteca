package com.bibliotech.bibliotech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "estanteprateleira", schema = "adelino_cunha")
public class Estanteprateleira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estante", nullable = false)
    //@Size(min = 1, max = 1, message = "A estante deve conter exatamente 1 caractere.")
    private String estante;     //na tabela e no documento fala que eh caracter

    @Column(name = "prateleira", nullable = false)
    private Integer prateleira;

}