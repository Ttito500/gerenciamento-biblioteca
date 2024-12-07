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
    private Integer estante;

    @Column(name = "prateleira", nullable = false)
    private Integer prateleira;

}