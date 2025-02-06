package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.EmprestimoRequestDTO;
import com.bibliotech.bibliotech.dtos.request.mappers.EmprestimoRequestMapper;
import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTO;
import com.bibliotech.bibliotech.dtos.response.mappers.EmprestimoResponseMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.models.Livro;
import com.bibliotech.bibliotech.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.List;

@Service
public class EmprestimosService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoRequestMapper emprestimoRequestMapper;
    private final EmprestimoResponseMapper emprestimoResponseMapper;

    public EmprestimosService(EmprestimoRepository emprestimoRepository, EmprestimoRequestMapper emprestimoRequestMapper, EmprestimoResponseMapper emprestimoResponseMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoRequestMapper = emprestimoRequestMapper;
        this.emprestimoResponseMapper = emprestimoResponseMapper;
    }

    public EmprestimoResponseDTO realizarEmprestimo (EmprestimoRequestDTO requestDTO) {
        if (requestDTO.getIdAluno() == null) {
            throw new ValidationException("O aluno não pode ser nulo");
        }
        if (requestDTO.getIdExemplar() == null) {
            throw new ValidationException("O exemplar não pode ser nulo");
        }

        Emprestimo emprestimo = emprestimoRequestMapper.toEntity(requestDTO);

        emprestimo.setSituacao("pendente");
        emprestimo.getAluno().setSituacao("debito");
        emprestimo.getExemplar().setSituacao("emprestado");

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        return emprestimoResponseMapper.toDto(emprestimoSalvo);
    }

    public Emprestimo alterarSituacao(Integer id, String status){
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + "não encontrado."));

        emprestimoExistente.setSituacao(status);

        emprestimoRepository.save(emprestimoExistente);
        return emprestimoExistente;
    }

    public Emprestimo renovarPrazo(Integer id){
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + "não encontrado."));

        if (ChronoUnit.DAYS.between(emprestimoExistente.getDataEmprestimo(), LocalDate.now()) > 30) {
            throw new ValidationException("Renovação não permitida. O prazo máximo para renovação foi excedido.");
        }


        if (emprestimoExistente.getQtdRenovacao() >= 3){
            throw new ValidationException("Renovação não permitida. O número máximo de renovações foi atingido");
        }

        if (emprestimoExistente.getSituacao().equals("atrasado")){
            emprestimoExistente.setDataPrazo(LocalDate.now().plusDays(7));
            emprestimoExistente.setSituacao("pendente");
        }
        else {
            emprestimoExistente.setDataPrazo(emprestimoExistente.getDataPrazo().plusDays(7));
        }

        emprestimoExistente.setQtdRenovacao(emprestimoExistente.getQtdRenovacao() + 1);

        emprestimoRepository.save(emprestimoExistente);
        return emprestimoExistente;
    }

    public List<Emprestimo> getEmprestimos(){ return emprestimoRepository.findAll(); }



}
