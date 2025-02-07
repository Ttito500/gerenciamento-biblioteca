package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.EmprestimoRequestDTO;
import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTO;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.services.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseEntity<Page<EmprestimoResponseDTO>> consultarEmprestimos(
            @RequestParam(value = "nomeAluno", required = false) String nomeAluno,
            @RequestParam(value = "tituloLivro", required = false) String tituloLivro,
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "situacao", required = false) String situacao,
            @RequestParam(value = "nomeRealizadoPor", required = false) String nomeRealizadoPor,
            @RequestParam(value = "dataEmprestimo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEmprestimo,
            @RequestParam(value = "nomeConcluidoPor", required = false) String nomeConcluidoPor,
            @RequestParam(value = "dataPrazo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPrazo,
            @RequestParam(value = "dataConclusao", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataConclusao,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<EmprestimoResponseDTO> emprestimosDTO = emprestimosService.consultarEmprestimos(
                nomeAluno, tituloLivro, isbn, situacao, nomeRealizadoPor,
                dataEmprestimo,
                nomeConcluidoPor,
                dataPrazo, dataConclusao, pageable);

        return ResponseEntity.ok(emprestimosDTO);
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<Page<EmprestimoResponseDTO>> consultarEmprestimosPorAluno(
            @PathVariable Integer idAluno,  // Recebendo o id do aluno na URL
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<EmprestimoResponseDTO> emprestimosDTO = emprestimosService.consultarEmprestimosPorAluno(idAluno, pageable);  // Chamando o service com o idAluno

        return ResponseEntity.ok(emprestimosDTO);
    }


    @PatchMapping("/alterarSituacao/{id}")
public ResponseEntity<Emprestimo> alterarSituacao(@PathVariable Integer id, @RequestParam String situacao){
    Emprestimo emprestimo = emprestimosService.alterarSituacao(id, situacao);
    return ResponseEntity.ok(emprestimo);
}

@PatchMapping("/renovarPrazo/{id}")
public ResponseEntity<String> renovarPrazo(@PathVariable Integer id){
    Emprestimo emprestimo = emprestimosService.renovarPrazo(id);
    return ResponseEntity.ok("Prazo renovado com sucesso");
}
}
