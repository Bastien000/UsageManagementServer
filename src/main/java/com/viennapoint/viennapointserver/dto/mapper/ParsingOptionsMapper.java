package com.viennapoint.viennapointserver.dto.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.viennapoint.viennapointserver.dto.ParsingOptionsDTO;
import com.viennapoint.viennapointserver.entity.ParsingOptionsEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class ParsingOptionsMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ParsingOptionsDTO toDTO(ParsingOptionsEntity entity) {
        ParsingOptionsDTO dto = new ParsingOptionsDTO();
        dto.setParsingOptionsEntityID(entity.getParsingOptionsEntityID());

        try {
            if (entity.getRemapingOptionsJSON() != null && !entity.getRemapingOptionsJSON().isEmpty()) {
                Map<String, List<String>> remapingOptions = mapper.readValue(
                        entity.getRemapingOptionsJSON(),
                        new TypeReference<Map<String, List<String>>>() {}
                );
                dto.setRemapingOptions(remapingOptions);
            }

            if (entity.getSplitOptionsJSON() != null) {
                dto.setSplitOptions(mapper.readValue(
                        entity.getSplitOptionsJSON(),
                        mapper.getTypeFactory().constructCollectionType(
                                List.class, mapper.getTypeFactory().constructMapType(Map.class, String.class, Double.class)
                        )
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error converting entity to DTO", e);
        }

        return dto;
    }

    public static ParsingOptionsEntity toEntity(ParsingOptionsDTO dto) {
        ParsingOptionsEntity entity = new ParsingOptionsEntity();
        entity.setParsingOptionsEntityID(dto.getParsingOptionsEntityID());

        try {
            if (dto.getRemapingOptions() != null) {
                entity.setRemapingOptionsJSON(mapper.writeValueAsString(dto.getRemapingOptions()));
            }

            if (dto.getSplitOptions() != null) {
                entity.setSplitOptionsJSON(mapper.writeValueAsString(dto.getSplitOptions()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error converting DTO to entity", e);
        }

        return entity;
    }
}