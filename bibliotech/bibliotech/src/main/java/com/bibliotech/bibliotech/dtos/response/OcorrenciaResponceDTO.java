package com.bibliotech.bibliotech.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaResponceDTO {
    private Integer id;
    private AlunoResponseDTO aluno;
    private UsuarioResponseDTO registradaPor;

}
