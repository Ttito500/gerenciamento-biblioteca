package com.bibliotech.bibliotech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "usuario", schema = "adelino_cunha", uniqueConstraints = {
        @UniqueConstraint(name = "usuario_email_key", columnNames = {"email"})
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_ultimo_acesso")
    private Instant dataUltimoAcesso;

}