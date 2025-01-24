package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.services.LivrosService;
import com.bibliotech.bibliotech.services.TurmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

}
