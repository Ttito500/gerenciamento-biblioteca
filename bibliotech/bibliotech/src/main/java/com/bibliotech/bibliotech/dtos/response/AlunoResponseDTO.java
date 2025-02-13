package com.bibliotech.bibliotech.dtos.response;

import com.bibliotech.bibliotech.dtos.response.mappers.TurmaResponseMapper;
import com.bibliotech.bibliotech.services.TurmasService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class AlunoResponseDTO {
    @Autowired
    TurmasService turmaService;

    @Autowired
    TurmaResponseMapper turmaResponseMapper;

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private TurmaResponseDTO turma;
    private String situacao;
    private boolean ativo;
    private Integer quantidadeEmprestimos;

    public AlunoResponseDTO(){
    }

    public AlunoResponseDTO(String nome, Integer idTurma, Integer quantidadeEmprestimos){
        this.nome = nome;
        this.turma = turmaResponseMapper.toDto(turmaService.getTurmaById(idTurma));
        this.quantidadeEmprestimos = quantidadeEmprestimos;
    }
}