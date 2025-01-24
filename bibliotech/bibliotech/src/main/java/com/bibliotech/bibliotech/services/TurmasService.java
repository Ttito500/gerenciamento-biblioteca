package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmasService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma cadastrarTurma(Turma turma){
        return turmaRepository.save(turma);
    }

}