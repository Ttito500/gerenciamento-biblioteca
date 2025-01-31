package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("")
    public ResponseEntity<List<Autor>> cadastrarAutores(@RequestBody List<Autor> autores) {
        List<Autor> autoresCriados = autorService.cadastrarAutores(autores);
        return ResponseEntity.status(HttpStatus.CREATED).body(autoresCriados);
    }
    
    //para teste
    @GetMapping("")
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autores = autorService.getAll();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Autor> buscarAutorPorNome(@RequestParam String nome) {
        Optional<Autor> autor = autorService.buscarPorNome(nome);
        return ResponseEntity.ok(autor.get());
    }

    @DeleteMapping("/sem-associacao")
    public ResponseEntity<Void> deletarAutoresSemAssociacao() {
        autorService.deletarAutoresSemAssociacao();
        return ResponseEntity.noContent().build();
    }
}
