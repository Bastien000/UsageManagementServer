package com.viennapoint.viennapointserver.dto.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viennapoint.viennapointserver.dto.CreatorOptionsDTO;
import com.viennapoint.viennapointserver.entity.CreatorOptionsEntity;

import java.util.Map;

public class CreatorOptionsMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static CreatorOptionsDTO toDTO(CreatorOptionsEntity entity) {
        CreatorOptionsDTO dto = new CreatorOptionsDTO();
        dto.setCreatorOptionsEntityID(entity.getCreatorOptionsEntityID());

        try {
            if (entity.getCreatorOptionsJSON() != null && !entity.getCreatorOptionsJSON().isEmpty()) {
                Map<String, Object> creatorOptions = mapper.readValue(
                        entity.getCreatorOptionsJSON(),
                        new TypeReference<Map<String, Object>>() {}
                );
                dto.setCreatorOptions(creatorOptions);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error converting entity to DTO", e);
        }

        return dto;
    }

    public static CreatorOptionsEntity toEntity(CreatorOptionsDTO dto) {
        CreatorOptionsEntity entity = new CreatorOptionsEntity();
        entity.setCreatorOptionsEntityID(dto.getCreatorOptionsEntityID());

        try {
            System.out.println(dto.getCreatorOptions() != null);
            if (dto.getCreatorOptions() != null) {
                entity.setCreatorOptionsJSON(mapper.writeValueAsString(dto.getCreatorOptions()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error converting DTO to entity", e);
        }

        return entity;
    }
}