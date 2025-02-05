package com.bibliotech.bibliotech.dtos.response.mappers;

import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTO;
import com.bibliotech.bibliotech.models.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoResponseMapper {
    public EmprestimoResponseDTO toDto(Emprestimo emprestimo) {

        if (emprestimo == null) {
            return null;
        }

        EmprestimoResponseDTO dto = new EmprestimoResponseDTO();

        dto.setId(emprestimo.getId());
        dto.setAlunoId(emprestimo.getAluno().getId());
        dto.setExemplarId(emprestimo.getExemplar().getId());
        dto.setRealizadoPor(emprestimo.getRealizadoPor().getNome());
        dto.setConcluidoPor(emprestimo.getConcluidoPor().getNome());
        dto.setObservacao(emprestimo.getObservacao());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataPrazo(emprestimo.getDataPrazo());
        dto.setDataConclusao(emprestimo.getDataConclusao());
        dto.setQtdRenovacao(emprestimo.getQtdRenovacao());
        dto.setSituacao(emprestimo.getSituacao());

        return dto;
    }
}
