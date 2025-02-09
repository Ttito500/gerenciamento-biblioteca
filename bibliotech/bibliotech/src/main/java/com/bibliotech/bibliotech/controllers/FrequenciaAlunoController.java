package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.dtos.request.FrequenciaAlunosRequestDTO;
import com.bibliotech.bibliotech.dtos.response.FrequenciaAlunosResponceDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.FrequenciaAlunosResponceMapper;
import com.bibliotech.bibliotech.models.FrequenciaAlunos;
import com.bibliotech.bibliotech.services.FrequenciaAlunosService;
import com.bibliotech.bibliotech.services.PdfExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/frequencia-alunos")
public class FrequenciaAlunoController {
    private final FrequenciaAlunosService frequenciaAlunosService;
    private final FrequenciaAlunosResponceMapper frequenciaAlunosResponceMapper;
    private final PdfExportService pdfExportService;

    public FrequenciaAlunoController(FrequenciaAlunosService frequenciaAlunosService, FrequenciaAlunosResponceMapper frequenciaAlunosResponceMapper, PdfExportService pdfExportService) {
        this.frequenciaAlunosService = frequenciaAlunosService;
        this.frequenciaAlunosResponceMapper = frequenciaAlunosResponceMapper;
        this.pdfExportService = pdfExportService;
    }

    @PostMapping
    public ResponseEntity<FrequenciaAlunosResponceDTO> registrarFrequencia(@RequestBody FrequenciaAlunosRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(frequenciaAlunosResponceMapper.toDto(frequenciaAlunosService.registrarFrequencia(requestDTO)));
    }

    @GetMapping
    public ResponseEntity<List<FrequenciaAlunosResponceDTO>> filtrarFrequencias(@RequestParam(value = "data", required = false) LocalDate data) {
        return ResponseEntity.ok(frequenciaAlunosResponceMapper.toDtoList(frequenciaAlunosService.filtrarFrequencias(data)));
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportFrequenciasPdf(@RequestParam LocalDate data) {
        List<FrequenciaAlunos> frequencias = frequenciaAlunosService.filtrarFrequencias(data);

        byte[] pdfBytes = pdfExportService.exportFrequenciaAlunosToPdf(frequencias);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio-frequencia-" + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}