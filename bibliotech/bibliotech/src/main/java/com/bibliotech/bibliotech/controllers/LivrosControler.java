package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/livros")
public class LivrosControler {

    @Autowired
    private LivrosService livrosService;

    @PostMapping("")
    public ResponseEntity<Livro> criarLivro (@RequestBody Livro body){

        Livro livro = livrosService.cadastrarLivro(body);
        URI location = URI.create("livros" + livro.getId()); // não sei muito bem oq isso faz, to seguindo exemplo do codigo de GC
        return ResponseEntity.created(location).body(livro);
    }

}
