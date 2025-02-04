package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.TurmaRequestDTO;
import com.bibliotech.bibliotech.dtos.response.TurmaResponseDTO;
import com.bibliotech.bibliotech.services.TurmasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmasService turmasService;

    @PostMapping("")
    public ResponseEntity<TurmaResponseDTO> criarTurma(@RequestBody TurmaRequestDTO requestDTO){

        TurmaResponseDTO turmaResponseDTO = turmasService.cadastrarTurma(requestDTO);
        URI location = URI.create("/turmas/" + turmaResponseDTO.getId());
        return ResponseEntity.created(location).body(turmaResponseDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> getTurmaById(@PathVariable Integer id){
        return ResponseEntity.ok(turmasService.getTurmaById(id));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<TurmaResponseDTO>> filtrarTurmas(@RequestParam(required = false) Integer serie,
                                                                @RequestParam(required = false) String turma,
                                                                @RequestParam(required = false) Integer anoDeEntrada,
                                                                @RequestParam(required = false) Boolean ativo) {
        List<TurmaResponseDTO> turmasResponseDTO = turmasService.filtrarTurmas(serie, turma, anoDeEntrada, ativo);
        return ResponseEntity.ok(turmasResponseDTO);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<TurmaResponseDTO> alterarTurma(@PathVariable Integer id, @RequestBody TurmaRequestDTO requestDTO) {
        TurmaResponseDTO turmaAtualizadaResponseDTO = turmasService.alterarTurma(id, requestDTO);
        return ResponseEntity.ok(turmaAtualizadaResponseDTO);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarTurma(@PathVariable Integer id) {
        turmasService.inativarTurma(id);
        return ResponseEntity.noContent().build();
    }

}
