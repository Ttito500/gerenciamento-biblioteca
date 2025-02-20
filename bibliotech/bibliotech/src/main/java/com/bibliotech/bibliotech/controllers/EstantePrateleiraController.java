package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.response.ExemplarResponseDTO;
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
    private EstantePrateleiraService estantePrateleiraService;

    @PostMapping
    public ResponseEntity<Estanteprateleira> criarEstanteprateleira(@RequestBody Estanteprateleira request) {
        return ResponseEntity.ok(estantePrateleiraService.adicionarEstanteprateleira(request));
    }

    @GetMapping
    public ResponseEntity<List<Estanteprateleira>> listarEstanteprateleira() {
        return ResponseEntity.ok(estantePrateleiraService.listarEstanteprateleiras());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarEstanteprateleira(@PathVariable Integer id, @RequestBody Estanteprateleira body) {
        return ResponseEntity.ok(estantePrateleiraService.atualizarEstanteprateleira(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEstanteprateleira(@PathVariable Integer id) {
        return ResponseEntity.ok(estantePrateleiraService.deletarEstanteprateleira(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ExemplarResponseDTO>> listarExemplaresPorEstantePrateleira(@PathVariable Integer id) {
        return ResponseEntity.ok(estantePrateleiraService.listarExemplaresPorEstantePrateleira(id));
    }
}
