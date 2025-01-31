package com.bibliotech.bibliotech.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "exemplar")
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro idLivro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_secao", nullable = false)
    private Secao idSecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estante_prateleira")
    private Estanteprateleira idEstantePrateleira;

    @Column(name = "observacao", length = 500)
    private String observacao;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "situacao", length = 10)
    private String situacao = "disponivel"; // "disponivel", "emprestado", "extraviado"

    @OneToMany(mappedBy = "idExemplar")
    private Set<Emprestimo> emprestimos = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        this.situacao = "disponivel";
    }
}