package com.bibliotech.bibliotech.dtos.response.mappers;

import com.bibliotech.bibliotech.dtos.response.LivroResponseDTO;
import com.bibliotech.bibliotech.models.Livro;

public class LivroResponceMapper {
    public LivroResponseDTO toDto(Livro livro) {
        if (livro == null) {
            return null;
        }

        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(livro.getId());
        dto.setIsbn(livro.getIsbn());
        dto.setTitulo(livro.getTitulo());
        dto.setAtivo(livro.getAtivo());

        return dto;
    }
}
