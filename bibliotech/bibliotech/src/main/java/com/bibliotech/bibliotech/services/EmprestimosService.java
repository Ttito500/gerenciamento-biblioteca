package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.EmprestimoRequestDTO;
import com.bibliotech.bibliotech.dtos.request.EmprestimoRequestDTOConcluir;
import com.bibliotech.bibliotech.dtos.request.mappers.EmprestimoRequestMapper;
import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTO;
import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTOAluno;
import com.bibliotech.bibliotech.dtos.response.EmprestimoResponseDTOLivro;
import com.bibliotech.bibliotech.dtos.response.mappers.EmprestimoResponseMapper;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Emprestimo;
import com.bibliotech.bibliotech.repositories.*;
import com.bibliotech.bibliotech.specifications.EmprestimoSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmprestimosService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoRequestMapper emprestimoRequestMapper;
    private final EmprestimoResponseMapper emprestimoResponseMapper;
    private final EmprestimoSpecification emprestimoSpecification;

    public EmprestimosService(EmprestimoRepository emprestimoRepository, EmprestimoRequestMapper emprestimoRequestMapper, EmprestimoSpecification emprestimoSpecification, EmprestimoResponseMapper emprestimoResponseMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoRequestMapper = emprestimoRequestMapper;
        this.emprestimoResponseMapper = emprestimoResponseMapper;
        this.emprestimoSpecification = emprestimoSpecification;
    }

    public EmprestimoResponseDTO realizarEmprestimo (EmprestimoRequestDTO requestDTO) {
        if (requestDTO.getIdAluno() == null) {
            throw new ValidationException("O aluno não pode ser nulo.");
        }
        if (requestDTO.getIdExemplar() == null) {
            throw new ValidationException("O exemplar não pode ser nulo.");
        }

        Emprestimo emprestimo = emprestimoRequestMapper.toEntity(requestDTO);

        emprestimo.setSituacao("pendente");
        emprestimo.getAluno().setSituacao("debito");
        emprestimo.getExemplar().setSituacao("emprestado");

        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        return emprestimoResponseMapper.toDto(emprestimoSalvo);
    }

    //CONSERTAR USUARIO DEPOIS
    public String cancelarEmprestimo(Integer id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + "não encontrado."));

        if (emprestimo.getSituacao().equals("cancelado")){
            throw new ValidationException("Emprestimo ja cancelado;");
        }

        emprestimo.setSituacao("cancelado");
        emprestimo.getAluno().setSituacao("regular");
        emprestimo.getExemplar().setSituacao("disponivel");

        emprestimo.setConcluidoPor(emprestimo.getRealizadoPor()); //TEMPORARIO

        emprestimo.setDataConclusao(LocalDate.now());

        emprestimoRepository.save(emprestimo);

        return "Emprestimo cancelado com sucesso;";
    }

    //CONSERTAR USUARIO DEPOIS
    public String concluirEmprestimo(Integer id, EmprestimoRequestDTOConcluir DTOConcluir){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + " não encontrado."));

        if (emprestimo.getSituacao().equals("cancelado") || emprestimo.getSituacao().equals("entregue") || emprestimo.getSituacao().equals("extraviado")){
            throw new ValidationException("Emprestimo ja concluido");
        }

        emprestimo.setObservacao(DTOConcluir.getObservacao());
        emprestimo.setDataConclusao(LocalDate.now());

        emprestimo.setConcluidoPor(emprestimo.getRealizadoPor()); //TEMPORARIO

        if (!DTOConcluir.isExtraviado()){
            emprestimo.setSituacao("entregue");
            emprestimo.getAluno().setSituacao("regular");
            emprestimo.getExemplar().setSituacao("disponivel");
        }else {
            emprestimo.setSituacao("extraviado");
            emprestimo.getAluno().setSituacao("irregular");
            emprestimo.getExemplar().setSituacao("extraviado");
        }

        emprestimoRepository.save(emprestimo);

        return DTOConcluir.isExtraviado() ? "Emprestimo extraviado com sucesso." : "Emprestimo concluido com sucesso.";
    }

    @Transactional
    public String renovarPrazo(Integer id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emprestimo com o ID" + id + " não encontrado."));

        if (emprestimo.getSituacao().equals("cancelado") || emprestimo.getSituacao().equals("entregue") || emprestimo.getSituacao().equals("extraviado")){
            throw new ValidationException("Emprestimo ja concluido.");
        }

        if (ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo(), LocalDate.now()) > 30) {
            throw new ValidationException("Renovação não permitida. O prazo máximo para renovação foi excedido.");
        }


        if (emprestimo.getQtdRenovacao() >= 3){
            throw new ValidationException("Renovação não permitida. O número máximo de renovações foi atingido.");
        }

        if (emprestimo.getSituacao().equals("atrasado")){
            emprestimo.setDataPrazo(LocalDate.now().plusDays(7));
            emprestimo.setSituacao("pendente");
        }
        else {
            emprestimo.setDataPrazo(emprestimo.getDataPrazo().plusDays(7));
        }

        emprestimo.setQtdRenovacao(emprestimo.getQtdRenovacao() + 1);

        emprestimoRepository.save(emprestimo);

        return "Prazo renovado com sucesso.";
    }

    public Page<EmprestimoResponseDTO> consultarEmprestimos(String nomeAluno, String tituloLivro, String isbn, String situacao, String nomeRealizadoPor, LocalDate dataEmprestimo, String nomeConcluidoPor, LocalDate dataPrazo, LocalDate dataConclusao, Pageable pageable) {

        Specification<Emprestimo> spec = emprestimoSpecification.buildSpecification(
                nomeAluno, tituloLivro, isbn, situacao, nomeRealizadoPor,
                dataEmprestimo, nomeConcluidoPor, dataPrazo, dataConclusao);

        Page<Emprestimo> emprestimos = emprestimoRepository.findAll(spec, pageable);

        return emprestimos.map(emprestimoResponseMapper::toDto);
    }

    public Page<EmprestimoResponseDTOAluno> consultarEmprestimosPorAluno(Integer idAluno, LocalDate dataEmprestimo, Pageable pageable) {
        Page<Emprestimo> emprestimos;

        if (dataEmprestimo != null) {
            emprestimos = emprestimoRepository.findByAlunoIdAndDataEmprestimo(idAluno, dataEmprestimo, pageable);
        } else {
            emprestimos = emprestimoRepository.findByAlunoId(idAluno, pageable);
        }

        return emprestimos.map(emprestimoResponseMapper::toDTOAluno);
    }

    public Page<EmprestimoResponseDTOLivro> consultarEmprestimosPorLivro(Integer idLivro, LocalDate dataEmprestimo, Pageable pageable) {
        Page<Emprestimo> emprestimos;

        if (dataEmprestimo != null) {
            emprestimos = emprestimoRepository.findByExemplar_LivroIdAndDataEmprestimo(idLivro, dataEmprestimo, pageable);
        } else {
            emprestimos = emprestimoRepository.findByExemplar_LivroId(idLivro, pageable);
        }

        return emprestimos.map(emprestimoResponseMapper::toDTOLivro);
    }


}
