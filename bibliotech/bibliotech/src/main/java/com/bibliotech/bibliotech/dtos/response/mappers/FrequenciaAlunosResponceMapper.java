package com.bibliotech.bibliotech.dtos.response.mappers;

import com.bibliotech.bibliotech.dtos.response.FrequenciaAlunosResponceDTO;
import com.bibliotech.bibliotech.models.FrequenciaAlunos;

import java.util.List;
import java.util.stream.Collectors;

public class FrequenciaAlunosResponceMapper {

    private final AlunoResponseMapper alunoResponseMapper;
    private final UsuarioResponseMapper usuarioResponseMapper;

    public FrequenciaAlunosResponceMapper(AlunoResponseMapper alunoResponseMapper, UsuarioResponseMapper usuarioResponseMapper) {
        this.alunoResponseMapper = alunoResponseMapper;
        this.usuarioResponseMapper = usuarioResponseMapper;
    }

    public FrequenciaAlunosResponceDTO toDto(FrequenciaAlunos frequenciaAlunos) {
        if (frequenciaAlunos == null) {
            return null;
        }

        FrequenciaAlunosResponceDTO dto = new FrequenciaAlunosResponceDTO();
        dto.setId(frequenciaAlunos.getId());
        dto.setAluno(alunoResponseMapper.toDto(frequenciaAlunos.getAluno()));
        dto.setRegistradaPor(usuarioResponseMapper.toDto(frequenciaAlunos.getRegistradaPor()));
        dto.setAtividade(frequenciaAlunos.getAtividade());
        dto.setData(frequenciaAlunos.getData());

        return dto;
    }

    public List<FrequenciaAlunosResponceDTO> toDtoList(List<FrequenciaAlunos> frequenciaAlunos) {
        return frequenciaAlunos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
