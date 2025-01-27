package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
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

    public Turma alterarTurma(Integer id, Turma novaTurma) {
        // Verifica se a turma existe
        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma com ID " + id + " não encontrada."));

        turmaExistente.setSerie(novaTurma.getSerie());
        turmaExistente.setTurma(novaTurma.getTurma());
        turmaExistente.setAnoDeEntrada(novaTurma.getAnoDeEntrada());
        turmaExistente.setAtivo(novaTurma.isAtivo());

        return turmaRepository.save(turmaExistente);
    }

    public void deletarTurma(Integer id) {
        // Verifica se a turma existe
        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma com ID " + id + " não encontrada."));

        turmaRepository.delete(turmaExistente);
    }
}