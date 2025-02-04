package com.bibliotech.bibliotech.dtos.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstanteprateleiraResponseDTO {
    private String estante;
    private Integer prateleira;
}
