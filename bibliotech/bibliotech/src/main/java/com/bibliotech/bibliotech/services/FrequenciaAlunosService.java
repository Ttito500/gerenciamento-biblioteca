package com.bibliotech.bibliotech.services;

import com.bibliotech.bibliotech.dtos.request.FrequenciaAlunosRequestDTO;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.FrequenciaAlunos;
import com.bibliotech.bibliotech.repositories.FrequenciaAlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class FrequenciaAlunosService {
    private final FrequenciaAlunosRepository frequenciaAlunosRepository;
    private final AlunosService alunosService;
    private final UsuarioService usuarioService;

    public FrequenciaAlunosService(FrequenciaAlunosRepository frequenciaAlunosRepository, AlunosService alunosService, UsuarioService usuarioService) {
        this.frequenciaAlunosRepository = frequenciaAlunosRepository;
        this.alunosService = alunosService;
        this.usuarioService = usuarioService;
    }

    public FrequenciaAlunos registrarFrequencia(FrequenciaAlunosRequestDTO requestDTO) {
        if (requestDTO.getIdAluno() == null) {
            throw new ValidationException("O aluno não pode ser nulo.");
        }
        if (requestDTO.getRegistradaPor() == null) {
            throw new ValidationException("O usuário que registrou a frequência não pode ser nulo.");
        }
        if (requestDTO.getAtividade() == null || requestDTO.getAtividade().isEmpty()) {
            throw new ValidationException("A atividade não pode ser nula ou vazia.");
        }

        FrequenciaAlunos frequenciaAlunos = new FrequenciaAlunos();
        frequenciaAlunos.setAluno(alunosService.buscarAlunoPorId(requestDTO.getIdAluno()));
        frequenciaAlunos.setRegistradaPor(usuarioService.getUsuarioById(requestDTO.getRegistradaPor()));
        frequenciaAlunos.setAtividade(requestDTO.getAtividade());
        frequenciaAlunos.setData(LocalDate.now());

        return frequenciaAlunosRepository.save(frequenciaAlunos);
    }

    //fiz assim para continuar o padrao e para deixar mais facil de manter o codigo
    public List<FrequenciaAlunos> filtrarFrequencias(LocalDate data){
        return frequenciaAlunosRepository.filtrarFrequencias(data);
    }

    public void deletarFrequencia(Integer id){
        frequenciaAlunosRepository.deleteById(id);
    }

}
