package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.services.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimosService emprestimosService;

    @Autowired
    public EmprestimoController(EmprestimosService emprestimosService) {
        this.emprestimosService = emprestimosService;
    }

    @PostMapping("")
    public ResponseEntity<Emprestimo> realizarEmprestimo(@RequestBody Emprestimo body){
        Integer alunoId = body.getIdAluno().getId();
        Integer livroId = body.getIdLivro().getId();

        Emprestimo emprestimo = emprestimosService.realizarEmprestimo(alunoId, livroId);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping("")
    public ResponseEntity<List<Emprestimo>> getEmprestimos(){
        List<Emprestimo> emprestimos = emprestimosService.getEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }
}
