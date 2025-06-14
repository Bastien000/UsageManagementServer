package com.viennapoint.viennapointserver.dto.mapper;


import com.viennapoint.viennapointserver.dto.UsageDTO;
import com.viennapoint.viennapointserver.entity.UsageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsageMapper {
    List<UsageDTO> toDTO(List<UsageEntity> source);
    UsageDTO toDTO(UsageEntity source);
    UsageEntity toEntity(UsageDTO source);
}
