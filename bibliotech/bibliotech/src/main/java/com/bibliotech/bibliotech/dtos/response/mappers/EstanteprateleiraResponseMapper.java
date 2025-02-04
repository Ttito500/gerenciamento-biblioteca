package com.bibliotech.bibliotech.dtos.response.mappers;

import com.bibliotech.bibliotech.dtos.response.EstanteprateleiraResponseDTO;
import com.bibliotech.bibliotech.models.Estanteprateleira;

public class EstanteprateleiraResponseMapper {

    public static EstanteprateleiraResponseDTO toDTO(Estanteprateleira entity) {
        if (entity == null) {
            return null;
        }

        EstanteprateleiraResponseDTO dto = new EstanteprateleiraResponseDTO();
        dto.setEstante(entity.getEstante());
        dto.setPrateleira(entity.getPrateleira());
        return dto;
    }

    public static Estanteprateleira toEntity(EstanteprateleiraResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Estanteprateleira entity = new Estanteprateleira();
        entity.setEstante(dto.getEstante());
        entity.setPrateleira(dto.getPrateleira());
        return entity;
    }
}