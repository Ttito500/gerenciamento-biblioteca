package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.FrequenciaAlunosRequestDTO;
import com.bibliotech.bibliotech.dtos.response.FrequenciaAlunosResponceDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.FrequenciaAlunosResponceMapper;
import com.bibliotech.bibliotech.models.FrequenciaAlunos;
import com.bibliotech.bibliotech.services.FrequenciaAlunosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

public class FrequenciaAlunoController {
    private final FrequenciaAlunosService frequenciaAlunosService;
    private final FrequenciaAlunosResponceMapper frequenciaAlunosResponceMapper;

    public FrequenciaAlunoController(FrequenciaAlunosService frequenciaAlunosService, FrequenciaAlunosResponceMapper frequenciaAlunosResponceMapper) {
        this.frequenciaAlunosService = frequenciaAlunosService;
        this.frequenciaAlunosResponceMapper = frequenciaAlunosResponceMapper;
    }

    @PostMapping
    public ResponseEntity<FrequenciaAlunosResponceDTO> registrarFrequencia(@RequestBody FrequenciaAlunosRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(frequenciaAlunosResponceMapper.toDto(frequenciaAlunosService.registrarFrequencia(requestDTO)));
    }

    @GetMapping
    public ResponseEntity<List<FrequenciaAlunosResponceDTO>> filtrarFrequencias(@RequestParam(value = "data", required = false) LocalDate data) {
        return ResponseEntity.ok(frequenciaAlunosResponceMapper.toDtoList(frequenciaAlunosService.filtrarFrequencias(data)));
    }
}
