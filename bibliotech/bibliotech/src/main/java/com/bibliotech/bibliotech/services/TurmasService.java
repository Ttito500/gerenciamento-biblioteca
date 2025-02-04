package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.TurmaRequestDTO;
import com.bibliotech.bibliotech.dtos.request.mappers.TurmaRequestMapper;
import com.bibliotech.bibliotech.dtos.response.TurmaResponseDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.TurmaResponseMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmasService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunosService alunosService;

    @Autowired
    private TurmaRequestMapper turmaRequestMapper;

    @Autowired
    private TurmaResponseMapper turmaResponseMapper;

    public TurmaResponseDTO cadastrarTurma(TurmaRequestDTO requestDTO) {
        if (requestDTO.getSerie() == null || requestDTO.getSerie() < 1) {
            throw new ValidationException("Série é obrigatória e deve ser maior que zero.");
        }
        if (requestDTO.getTurma() == null || requestDTO.getTurma().length() > 1) {
            throw new ValidationException("Turma é obrigatória e deve ter no máximo 1 caractere.");
        }
        if (requestDTO.getAnoDeEntrada() == null || requestDTO.getAnoDeEntrada() <= 0) {
            throw new ValidationException("Ano de entrada é obrigatório e deve ser maior que zero.");
        }
        if (turmaRepository.existsBySerieAndTurmaAndAnoDeEntrada(requestDTO.getSerie(), requestDTO.getTurma(), requestDTO.getAnoDeEntrada())) {
            throw new ValidationException("Já existe uma turma com essa combinação de série, turma e ano de entrada.");
        }

        requestDTO.setTurma(requestDTO.getTurma().toUpperCase());

        Turma turma = turmaRequestMapper.toEntity(requestDTO);
        turma.setAtivo(true);
        Turma turmaSalva = turmaRepository.save(turma);
        return turmaResponseMapper.toDto(turmaSalva);
    }

    public TurmaResponseDTO getTurmaById(Integer id){
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma com ID " + id + " não encontrada."));
        return turmaResponseMapper.toDto(turma);
    }

    public List<TurmaResponseDTO> filtrarTurmas(Integer serie, String turma, Integer anoDeEntrada, Boolean ativo) {
        List<Turma> turmas = turmaRepository.filtrarTurmas(serie, turma, anoDeEntrada, ativo);
        return turmas.stream()
                .map(turmaResponseMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<TurmaResponseDTO> listarTurmas() {
        List<Turma> turmas = turmaRepository.findAll();
        return turmas.stream()
                .map(turmaResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    public TurmaResponseDTO alterarTurma(Integer id, TurmaRequestDTO novaTurmaDTO) {
        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma com ID " + id + " não encontrada."));

        if (turmaRepository.existsBySerieAndTurmaAndAnoDeEntrada(novaTurmaDTO.getSerie(), novaTurmaDTO.getTurma(), novaTurmaDTO.getAnoDeEntrada())) {
            throw new ValidationException("Já existe uma turma com essa combinação de série, turma e ano de entrada.");
        }

        turmaExistente.setSerie(novaTurmaDTO.getSerie());
        turmaExistente.setTurma(novaTurmaDTO.getTurma().toUpperCase());
        turmaExistente.setAnoDeEntrada(novaTurmaDTO.getAnoDeEntrada());

        Turma turmaAtualizada = turmaRepository.save(turmaExistente);
        return turmaResponseMapper.toDto(turmaAtualizada);
    }

    public void inativarTurma(Integer id) {

        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma com ID " + id + " não encontrada."));

        alunosService.inativarAlunosPorTurma(turmaExistente);
        turmaExistente.setAtivo(false);
        turmaRepository.save(turmaExistente);
    }
}