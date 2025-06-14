package com.viennapoint.viennapointserver.dto.mapper;

import com.viennapoint.viennapointserver.dto.BuildingDTO;
import com.viennapoint.viennapointserver.dto.TenantDTO;
import com.viennapoint.viennapointserver.entity.BuildingEntity;
import com.viennapoint.viennapointserver.entity.TenantEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    List<TenantDTO> toDTO(List<TenantEntity> source);
    TenantDTO toDTO(TenantEntity source);
    TenantEntity toEntity(TenantDTO source);
}

