package com.viennapoint.viennapointserver.dto.mapper;


import com.viennapoint.viennapointserver.dto.BuildingDTO;
import com.viennapoint.viennapointserver.entity.BuildingEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    List<BuildingDTO> toDTO(List<BuildingEntity> source);
    BuildingDTO toDTO(BuildingEntity source);
    BuildingEntity toEntity(BuildingDTO source);
}
