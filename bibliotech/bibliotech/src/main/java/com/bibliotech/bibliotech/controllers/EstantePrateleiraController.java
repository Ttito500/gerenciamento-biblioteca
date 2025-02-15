package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.models.Estanteprateleira;
import com.bibliotech.bibliotech.services.EstantePrateleiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estanteprateleira")
public class EstantePrateleiraController {

    @Autowired
    private EstantePrateleiraService estateprateleiraService;

    @PostMapping
    public ResponseEntity<Estanteprateleira> criarEstanteprateleira(@RequestBody Estanteprateleira request) {
        return ResponseEntity.ok(estateprateleiraService.adicionarEstanteprateleira(request));
    }

    @GetMapping
    public ResponseEntity<List<Estanteprateleira>> listarEstanteprateleira() {
        return ResponseEntity.ok(estateprateleiraService.listarEstanteprateleiras());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estanteprateleira> atualizarEstanteprateleira(@PathVariable Integer id, @RequestBody Estanteprateleira body) {
        return ResponseEntity.ok(estateprateleiraService.atualizarEstanteprateleira(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEstanteprateleira(@PathVariable Integer id) {
        return ResponseEntity.ok(estateprateleiraService.deletarEstanteprateleira(id));
    }
}
