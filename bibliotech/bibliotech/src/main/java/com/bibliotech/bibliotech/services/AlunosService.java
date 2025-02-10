package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.AlunoRequestDTO;
import com.bibliotech.bibliotech.dtos.request.mappers.AlunoRequestMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Turma;
import com.bibliotech.bibliotech.repositories.AlunoRepository;
import com.bibliotech.bibliotech.repositories.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunosService {

    private final AlunoRepository alunoRepository;
    private final AlunoRequestMapper alunoRequestMapper;
    private final TurmaRepository turmaRepository;

    public AlunosService(AlunoRepository alunoRepository, AlunoRequestMapper alunoRequestMapper, TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.alunoRequestMapper = alunoRequestMapper;
        this.turmaRepository = turmaRepository;
    }

    public List<Aluno> filtrarAlunos(Integer serie, String turma, String nome, String situacao, Boolean ativo) {
        return  alunoRepository.filtrarAlunos(serie, turma, nome, ativo, situacao);
    }

    public Aluno buscarAlunoPorId(Integer id) {
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado."));
    }

    @Transactional
    public Aluno cadastrarAluno(AlunoRequestDTO requestDTO) {
        if (requestDTO.getIdTurma() == null) {
            throw new ValidationException("A turma não pode ser nula.");
        }
        if (requestDTO.getNome() == null || requestDTO.getNome().isEmpty()) {
            throw new ValidationException("O nome do aluno é obrigatório.");
        }
        if (requestDTO.getEmail() == null || requestDTO.getEmail().isEmpty()) {
            throw new ValidationException("O e-mail do aluno é obrigatório.");
        }
        if (alunoRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ValidationException("Já existe um aluno cadastrado com esse e-mail.");
        }

        Aluno aluno = alunoRequestMapper.toEntity(requestDTO);
        aluno.setSituacao("regular");

        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizarAluno(Integer id, AlunoRequestDTO requestDTO) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado."));

        if (!alunoExistente.getEmail().equals(requestDTO.getEmail()) && alunoRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ValidationException("Já existe um aluno cadastrado com esse e-mail.");
        }

        alunoExistente.setNome(requestDTO.getNome());
        alunoExistente.setEmail(requestDTO.getEmail());
        alunoExistente.setTelefone(requestDTO.getTelefone());

        String situacao = requestDTO.getSituacao();
        if (situacao != null && !situacao.isEmpty() && !situacao.equals("regular") && !situacao.equals("debito") && !situacao.equals("irregular")) {
            throw new ValidationException("Situação inválida. Deve ser 'regular', 'debito' ou 'irregular'.");
        }
        alunoExistente.setSituacao(situacao);

        if (requestDTO.getIdTurma() != null) {
            Turma turmaExistente = turmaRepository.findById(requestDTO.getIdTurma())
                    .orElseThrow(() -> new ValidationException("Turma não encontrada."));
            alunoExistente.setTurma(turmaExistente);
        }

        return alunoRepository.save(alunoExistente);
    }

    @Transactional
    public void inativarAluno(Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado."));

        if (alunoRepository.temSituacaoIrregular(id)) {
            throw new ValidationException("Aluno possui pendências e não pode ser inativado.");
        }

        alunoExistente.setAtivo(false);
        alunoRepository.save(alunoExistente);
    }

    public void inativarAlunosPorTurma(Turma turma) {
        List<Aluno> alunos = alunoRepository.filtrarAlunos(turma.getSerie(), turma.getTurma(), null, true, null);
        for (Aluno aluno : alunos) {
            aluno.setAtivo(false);
        }
        alunoRepository.saveAll(alunos);
    }
}