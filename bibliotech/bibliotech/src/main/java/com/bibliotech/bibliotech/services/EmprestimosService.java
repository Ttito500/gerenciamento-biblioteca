package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.mappers.EmprestimoRequestMapper;
import com.bibliotech.bibliotech.dtos.response.mappers.EmprestimoResponseMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
public class EmprestimosService {

    private final AlunoRepository alunoRepository;
    private final ExemplarRepository exemplarRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoRequestMapper emprestimoRequestMapper;
    private final EmprestimoResponseMapper emprestimoResponseMapper;

    public EmprestimosService(AlunoRepository alunoRepository, ExemplarRepository exemplarRepository, EmprestimoRepository emprestimoRepository, EmprestimoRequestMapper emprestimoRequestMapper, EmprestimoResponseMapper emprestimoResponseMapper) {
        this.alunoRepository = alunoRepository;
        this.exemplarRepository = exemplarRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoRequestMapper = emprestimoRequestMapper;
        this.emprestimoResponseMapper = emprestimoResponseMapper;
    }


    //Ainda tem que fazer as excessoes bonitinhas
    public Emprestimo realizarEmprestimo (Integer alunoId, Integer livroId, Integer qtdRenovacao ,String observacao) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (!"regular".equalsIgnoreCase(aluno.getSituacao())){
            throw new RuntimeException("Erro de situação");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrazo(LocalDate.now().plusDays(7));
        emprestimo.setQtdRenovacao(qtdRenovacao);
        emprestimo.setSituacao(emprestimo.getSituacao() != null ? emprestimo.getSituacao() : "pendente");
        emprestimo.setObservacao(observacao);


        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo alterarSituacao(Integer id, String status){
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + "não encontrado."));

        emprestimoExistente.setSituacao(status);

        emprestimoRepository.save(emprestimoExistente);
        return emprestimoExistente;
    }

    public Emprestimo renovarPrazo(Integer id){
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + "não encontrado."));

        if (emprestimoExistente.getSituacao().equals("atrasado")){
            emprestimoExistente.setDataPrazo(LocalDate.now().plusDays(7));
        }
        else {
            emprestimoExistente.setDataPrazo(emprestimoExistente.getDataPrazo().plusDays(7));
        }

        emprestimoRepository.save(emprestimoExistente);
        return emprestimoExistente;
    }

    public List<Emprestimo> getEmprestimos(){ return emprestimoRepository.findAll(); }



}
