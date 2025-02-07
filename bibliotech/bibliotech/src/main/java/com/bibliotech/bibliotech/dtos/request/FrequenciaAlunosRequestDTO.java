package com.bibliotech.bibliotech.dtos.request;

import lombok.Getter;
import lombok.Setter;

//n precisa de data pois a data é cadastrada automaticamente e sempre vai ser do dia atual
@Getter
@Setter
public class FrequenciaAlunosRequestDTO {
    private Integer id;
    private Integer idAluno;
    private Integer registradaPor; //id do usuario
    private String atividade;
}
