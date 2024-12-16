package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunosService {
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(Aluno aluno){
        alunoRepository.save(aluno);
        return aluno;
    }

    public List<Aluno> getAlunos(){return alunoRepository.findAll();}

    public Optional<Aluno> getAlunoById(Integer id){return alunoRepository.findById(Long.valueOf(id));}

    public Aluno alterarAluno(Integer id, Aluno novoAluno) {
        Aluno alunoExistente = alunoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Aluno com ID " + id + " não encontrado."));

        alunoExistente.setNome(novoAluno.getNome());
        alunoExistente.setEmail(novoAluno.getEmail());
        alunoExistente.setTelefone(novoAluno.getTelefone());
        alunoExistente.setSituacao(novoAluno.getSituacao());

        // Se houver necessidade de atualizar a turma:
        if (novoAluno.getIdTurma() != null) {
            alunoExistente.setIdTurma(novoAluno.getIdTurma());
        }

        return alunoRepository.save(alunoExistente);
    }

    public void deletarAluno(Integer id) {
        Aluno aluno = alunoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Aluno com ID " + id + " não encontrado."));

        alunoRepository.delete(aluno);
    }

}
