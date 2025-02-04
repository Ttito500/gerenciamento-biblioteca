package com.bibliotech.bibliotech.dtos.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExemplarResponseDTO {
    private Integer id;
    private EstanteprateleiraResponseDTO estanteprateleira;
    private SecaoResponseDTO secao;
    private String observacao;
    private Integer numero;
    private String situacao = "disponivel";
}
