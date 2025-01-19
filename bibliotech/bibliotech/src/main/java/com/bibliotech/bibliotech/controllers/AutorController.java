package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Autor;
import com.bibliotech.bibliotech.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autores = autorService.getAll();
        return ResponseEntity.ok(autores);
    }
}
