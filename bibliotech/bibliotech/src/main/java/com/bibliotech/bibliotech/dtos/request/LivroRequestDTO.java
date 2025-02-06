package com.bibliotech.bibliotech.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDTO {
    private Integer id;
    private String isbn;
    private String titulo;
}
