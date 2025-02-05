package com.bibliotech.bibliotech.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimoRequestDTO {
    private Integer alunoId;
    private Integer exemplarId;
    private String observacao;
}