package com.bibliotech.bibliotech.dtos.request.mappers;

import com.bibliotech.bibliotech.dtos.request.EmprestimoRequestDTO;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.models.Exemplar;
import com.bibliotech.bibliotech.repositories.AlunoRepository;
import com.bibliotech.bibliotech.repositories.ExemplarRepository;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component
public class EmprestimoRequestMapper {
    private final AlunoRepository alunoRepository;
    private final ExemplarRepository exemplarRepository;

    public EmprestimoRequestMapper(AlunoRepository alunoRepository, ExemplarRepository exemplarRepository) {
        this.alunoRepository = alunoRepository;
        this.exemplarRepository = exemplarRepository;
    }

    public Emprestimo toEntity(EmprestimoRequestDTO EmprestimoDto){
        Aluno aluno = alunoRepository.findById(EmprestimoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        Exemplar exemplar = exemplarRepository.findById(EmprestimoDto.getExemplarId())
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));

        if (EmprestimoDto == null){
            return null;
        }

        if(!"regular".equalsIgnoreCase(aluno.getSituacao())){
            throw new ValidationException("O aluno não está com a situação regular");
        }

        if(!"disponivel".equalsIgnoreCase(exemplar.getSituacao())){
            throw new ValidationException("O exemplar não está disponivel");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno);
        emprestimo.setExemplar(exemplar);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrazo(LocalDate.now().plusDays(7));
        emprestimo.setQtdRenovacao(0);
        emprestimo.setObservacao(EmprestimoDto.getObservacao());
        //emprestimo.setRealizadoPor();

        aluno.setSituacao("debito");

        exemplar.setSituacao("emprestado");

        emprestimo.setSituacao("pendente");

        return emprestimo;
    }
}
