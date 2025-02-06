package com.bibliotech.bibliotech.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroResponseDTO {
    private Integer id;
    private String isbn;
    private String titulo;
    private boolean ativo;
}