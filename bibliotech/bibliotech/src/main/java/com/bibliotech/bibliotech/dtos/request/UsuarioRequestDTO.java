package com.bibliotech.bibliotech.dtos.request;

import com.bibliotech.bibliotech.models.CargosUsuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    private Integer id;
    private String nome;
    private String cargo; //converto para enum depois
    private String email;
    private String senha;
}
