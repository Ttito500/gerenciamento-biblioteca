package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    //para teste
    @PostMapping("")
    public ResponseEntity<Void> cadastrarAutores(@RequestBody List<Autor> autores) {
        if (autores == null || autores.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Extrai os nomes dos autores e chama o metodo addAutores
        List<String> nomesAutores = autores.stream()
                .map(Autor::getNome)
                .filter(nome -> nome != null && !nome.isEmpty())
                .toList();

        if (nomesAutores.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        autorService.addAutores(nomesAutores);
        return ResponseEntity.ok().build();
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
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/sem-associacao")
    public ResponseEntity<Void> deletarAutoresSemAssociacao() {
        autorService.deleteAutoresSemAssociacao();
        return ResponseEntity.noContent().build();
    }
}
