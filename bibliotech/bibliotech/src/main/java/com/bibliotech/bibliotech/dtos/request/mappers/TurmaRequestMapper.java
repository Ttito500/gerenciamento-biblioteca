package com.bibliotech.bibliotech.dtos.request.mappers;

import com.bibliotech.bibliotech.repositories.TurmaRepository;
import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.dtos.request.TurmaRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TurmaRequestMapper {

    public Turma toEntity(TurmaRequestDTO turmaRequestDTO) {
        if (turmaRequestDTO == null) {
            return null;
        }

        Turma turma = new Turma();
        turma.setId(turmaRequestDTO.getId());
        turma.setSerie(turmaRequestDTO.getSerie());
        turma.setTurma(turmaRequestDTO.getTurma());
        turma.setAnoDeEntrada(turmaRequestDTO.getAnoDeEntrada());

        return turma;
    }

}
