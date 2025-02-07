package com.bibliotech.bibliotech.dtos.response.mappers;

import com.bibliotech.bibliotech.dtos.mappers.AutorMapper;
import com.bibliotech.bibliotech.dtos.mappers.GeneroMapper;
import com.bibliotech.bibliotech.dtos.response.LivroResponseDTO;
import com.bibliotech.bibliotech.models.Livro;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LivroResponseMapper {

    private static final AutorMapper autorMapper = new AutorMapper();
    private static final GeneroMapper generoMapper = new GeneroMapper();

    public static LivroResponseDTO toDTO(Livro livro) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setIsbn(livro.getIsbn());
        dto.setAtivo(livro.getAtivo());
        dto.setAutores(autorMapper.toDTOList(livro.getAutores()));
        dto.setGeneros(generoMapper.toDTOList(livro.getGeneros()));

        return dto;
    }

    public static List<LivroResponseDTO> toDTOList(List<Livro> livros) {
        return livros.stream().map(LivroResponseMapper::toDTO).collect(Collectors.toList());
    }
}