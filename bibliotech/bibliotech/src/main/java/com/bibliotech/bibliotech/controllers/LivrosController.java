package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.LivroRequestPostDTO;
import com.bibliotech.bibliotech.dtos.response.LivroResponseDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.LivroResponseMapper;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivrosService livrosService;

    private final LivroResponseMapper livroResponseMapper;

    @Autowired
    public LivrosController(LivrosService livrosService, LivroResponseMapper livroResponseMapper) {
        this.livrosService = livrosService;
        this.livroResponseMapper = livroResponseMapper;
    }

    @PostMapping("")
    public ResponseEntity<LivroResponseDTO> criarLivro (@RequestBody LivroRequestPostDTO body) {
        LivroResponseDTO livroResponseDTO = livroResponseMapper.toDTO(livrosService.cadastrarLivro(body));
        URI location = URI.create("/livros/" + livroResponseDTO.getId());
        return ResponseEntity.created(location).body(livroResponseDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<LivroResponseDTO>> getLivros(){
        List<LivroResponseDTO> livroResponseDTO = livroResponseMapper.toDTOList(livrosService.getLivros());
        return ResponseEntity.ok(livroResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> getLivroById(@PathVariable Integer id){
        LivroResponseDTO livroResponseGetDTO = livroResponseMapper.toDTO(livrosService.getLivroById(id));
        return ResponseEntity.ok(livroResponseGetDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Integer id, @RequestBody Livro body){
        Livro livro = livrosService.atualizarLivro(id, body);
        return ResponseEntity.ok(livro);
    }
}
