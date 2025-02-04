package com.bibliotech.bibliotech.dtos.response.mappers;

import com.bibliotech.bibliotech.dtos.AutorDTO;
import com.bibliotech.bibliotech.dtos.GeneroDTO;
import com.bibliotech.bibliotech.dtos.response.ExemplarResponseDTO;
import com.bibliotech.bibliotech.dtos.response.LivroResponseDTO;
import com.bibliotech.bibliotech.models.Livro;

import java.util.List;

public class LivroResponseMapper {

    public static LivroResponseDTO toDTO(Livro livro, List<AutorDTO> autores, List<GeneroDTO> generos, List<ExemplarResponseDTO> exemplares) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setIsbn(livro.getIsbn());
        dto.setAtivo(livro.getAtivo());

        dto.setAutores(autores);
        dto.setGeneros(generos);
        dto.setExemplares(exemplares);
        return dto;
    }

    public static Livro toEntity(LivroResponseDTO dto) {
        Livro entity = new Livro();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAtivo(dto.isAtivo());

        return entity;
    }
}