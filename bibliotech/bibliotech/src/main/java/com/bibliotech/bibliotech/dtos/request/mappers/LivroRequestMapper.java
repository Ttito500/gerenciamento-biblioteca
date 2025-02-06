package com.bibliotech.bibliotech.dtos.request.mappers;

import com.bibliotech.bibliotech.dtos.request.LivroRequestDTO;
import com.bibliotech.bibliotech.models.Livro;

public class LivroRequestMapper {
    public Livro toEntity(LivroRequestDTO livroRequestDTO) {
        if (livroRequestDTO == null) {
            return null;
        }

        Livro livro = new Livro();
        livro.setId(livroRequestDTO.getId());
        livro.setIsbn(livroRequestDTO.getIsbn());
        livro.setTitulo(livroRequestDTO.getTitulo());

        return livro;
    }
}
