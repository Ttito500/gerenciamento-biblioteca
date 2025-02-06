package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.EmprestimoRequestDTO;
import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTO;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.services.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<EmprestimoResponseDTO> realizarEmprestimo(@RequestBody EmprestimoRequestDTO requestDTO){
            EmprestimoResponseDTO emprestimoResponseDTO = emprestimosService.realizarEmprestimo(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoResponseDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<Emprestimo>> getEmprestimos(){
        List<Emprestimo> emprestimos = emprestimosService.getEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }

    @PatchMapping("/alterarSituacao/{id}")
    public ResponseEntity<Emprestimo> alterarSituacao(@PathVariable Integer id, @RequestParam String situacao){
        Emprestimo emprestimo = emprestimosService.alterarSituacao(id, situacao);
        return ResponseEntity.ok(emprestimo);
    }

    @PatchMapping("/renovarPrazo/{id}")
    public ResponseEntity<Emprestimo> renovarPrazo(@PathVariable Integer id){
        Emprestimo emprestimo = emprestimosService.renovarPrazo(id);
        return ResponseEntity.ok(emprestimo);
    }
}
