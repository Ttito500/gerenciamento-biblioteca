package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.services.TurmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmasService turmasService;

    @PostMapping("")
    public ResponseEntity<Turma> criarTurma (@RequestBody Turma body){
        Turma turma = turmasService.cadastrarTurma(body);
        URI location = URI.create("/turmas/" + turma.getId());
        return ResponseEntity.created(location).body(turma);
    }

    @GetMapping("")
    public ResponseEntity<List<Turma>> getTurmas(){
        List<Turma> turmas = turmasService.getTurmas();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turma>> getTurmaById(@PathVariable Integer id){
        return ResponseEntity.ok(turmasService.getTurmaById(id));
    }

    @GetMapping("/filtrar")
    public List<Turma> filtrarTurmas(@RequestParam(required = false) Integer serie,
                                     @RequestParam(required = false) String turma,
                                     @RequestParam(required = false) Integer anoDeEntrada,
                                     @RequestParam(required = false) Boolean ativo) {
        return turmasService.filtrarTurmas(serie, turma, anoDeEntrada, ativo);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Turma> alterarTurma(@PathVariable Integer id, @RequestBody Turma body) {
        Turma turmaAtualizada = turmasService.alterarTurma(id, body);
        return ResponseEntity.ok(turmaAtualizada);
    }

    //não tinha nenhuma restrição nos requisitos, mas oq acontece numa turma q tem um aluno com emprestimo ativo e é inativada?
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer id) {
        turmasService.deletarTurma(id);
        return ResponseEntity.noContent().build();
    }

}
