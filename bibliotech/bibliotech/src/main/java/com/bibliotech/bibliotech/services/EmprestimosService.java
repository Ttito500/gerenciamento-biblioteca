package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.repositories.AlunoRepository;
import com.bibliotech.bibliotech.repositories.EmprestimoRepository;
import com.bibliotech.bibliotech.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class EmprestimosService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    //Ainda tem que fazer as excessoes bonitinhas
    public Emprestimo realizarEmprestimo (Integer alunoId, Integer livroId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));
        if (!"regular".equalsIgnoreCase(livro.getSituacao())){
            throw new RuntimeException("Aa");
        }

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (!"disponivel".equalsIgnoreCase(aluno.getSituacao())){
            throw new RuntimeException("Aa");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdAluno(aluno);
        emprestimo.setIdLivro(livro);
        //DATAS

        livro.setSituacao("emprestado");
        aluno.setSituacao("emprestado");

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> getEmprestimos(){ return emprestimoRepository.findAll(); }

}
