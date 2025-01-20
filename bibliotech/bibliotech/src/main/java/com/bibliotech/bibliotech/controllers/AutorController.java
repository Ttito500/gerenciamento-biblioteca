package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    //para teste
    @PostMapping ("")
    public ResponseEntity<Autor> cadastrarAutor(@RequestBody Autor body) {
        Autor autor = autorService.addAutor(body);
        URI locantion =URI.create("/autor" + autor.getId());
        return ResponseEntity.created(locantion).body(autor);
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

    @DeleteMapping("")
    public ResponseEntity<Void> deletarAutor(@RequestBody Autor body) {
        autorService.deleteAutor(body);
        return ResponseEntity.noContent().build();
    }


}
