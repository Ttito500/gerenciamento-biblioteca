package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Livroautor;
import com.bibliotech.bibliotech.services.LivroautorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livroautor") // Define o endpoint para esta rota
public class LivroautorController {

    @Autowired
    LivroautorService livroautorService;

    @PostMapping
    public ResponseEntity<Livroautor> cadastrarLivroautor(@RequestBody Livroautor body) {
        Livroautor livroautor = livroautorService.cadastrarLivroautor(body);
        URI locantion =URI.create("/livroautor" + livroautor.getId());
        return ResponseEntity.created(locantion).body(livroautor);
    }

    @GetMapping("")
    public ResponseEntity<List<Livroautor>> getLivrosautores(){
        List<Livroautor> livrosautores = livroautorService.getAllLivrosAutores();
        return ResponseEntity.ok(livrosautores);
    }
}
