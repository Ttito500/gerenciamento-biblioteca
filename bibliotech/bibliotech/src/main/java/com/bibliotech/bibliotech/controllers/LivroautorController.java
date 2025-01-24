package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Livroautor;
import com.bibliotech.bibliotech.services.LivroautorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// criada para teste, qualquer coisa podem apagar
@RestController
@RequestMapping("/livroautor")
public class LivroautorController {

    private final LivroautorService livroautorService;

    @Autowired
    public LivroautorController(LivroautorService livroautorService) {this.livroautorService = livroautorService;}

    @PostMapping
    public ResponseEntity<Livroautor> cadastrarLivroautor(@RequestBody Livroautor body) {

        if (body.getId_livro() == null || body.getId_autor() == null) {
            throw new IllegalArgumentException("Os campos 'id_livro' e 'id_autor' são obrigatórios.");
        }

        Integer livroId = body.getId_livro().getId();
        Integer autorId = body.getId_autor().getId();

        System.out.println(livroId + " " + autorId);

        Livroautor livroautor = livroautorService.cadastrarLivroautor(livroId, autorId);
        return ResponseEntity.ok(livroautor);
    }

    @GetMapping("")
    public ResponseEntity<List<Livroautor>> getLivrosautores(){
        List<Livroautor> livrosautores = livroautorService.getAllLivrosAutores();
        return ResponseEntity.ok(livrosautores);
    }
}
