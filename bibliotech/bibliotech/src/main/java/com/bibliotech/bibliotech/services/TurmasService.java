package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmasService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma cadastrarTurma(Turma turma){
        return turmaRepository.save(turma);
    }

    public List<Turma> getTurmas(){return turmaRepository.findAll();}

    public Optional<Turma> getTurmaById(Integer id){return turmaRepository.findById(id);}

    public List<Turma> filtrarTurmas(Integer serie, String turma, Integer anoDeEntrada, Boolean ativo) {
        return turmaRepository.filtrarTurmas(serie, turma, anoDeEntrada, ativo);
    }
}