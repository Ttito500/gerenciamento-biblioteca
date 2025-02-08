package com.bibliotech.bibliotech.dtos.request.mappers;

import com.bibliotech.bibliotech.dtos.request.FrequenciaAlunosRequestDTO;
import com.bibliotech.bibliotech.exception.NotFoundException;
import com.bibliotech.bibliotech.exception.ValidationException;
import com.bibliotech.bibliotech.models.Aluno;
import com.bibliotech.bibliotech.models.FrequenciaAlunos;
import com.bibliotech.bibliotech.models.Usuario;
import com.bibliotech.bibliotech.repositories.AlunoRepository;
import com.bibliotech.bibliotech.repositories.UsuarioRepository;
import com.bibliotech.bibliotech.services.AlunosService;
import com.bibliotech.bibliotech.services.UsuarioService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FrequenciaAlunosRequestMapper {
    private final AlunosService alunoRepository;
    private final UsuarioService usuarioRepository;

    public FrequenciaAlunosRequestMapper(AlunosService alunoRepository, UsuarioService usuarioRepository) {
        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public FrequenciaAlunos toEntity(FrequenciaAlunosRequestDTO frequenciaAlunosRequestDTO) {
        FrequenciaAlunos frequenciaAlunos = new FrequenciaAlunos();

        Aluno alunoExistente = alunoRepository.buscarAlunoPorId(frequenciaAlunosRequestDTO.getIdAluno());
        Usuario usuarioExistente = usuarioRepository.getUsuarioById(frequenciaAlunosRequestDTO.getRegistradaPor());


        if (frequenciaAlunosRequestDTO == null) {
            return null;
        }

        frequenciaAlunos.setAluno(alunoExistente);
        frequenciaAlunos.setRegistradaPor(usuarioExistente);
        frequenciaAlunos.setId(frequenciaAlunosRequestDTO.getId());
        frequenciaAlunos.setAtividade(frequenciaAlunosRequestDTO.getAtividade());

        return frequenciaAlunos;
    }
}
