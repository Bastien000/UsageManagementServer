package com.viennapoint.viennapointserver.service.addService;

import com.viennapoint.viennapointserver.dto.BuildingDTO;
import com.viennapoint.viennapointserver.dto.TenantDTO;

import java.util.Date;
import java.util.Optional;

public interface AddService {
    Optional<String> addBuilding(BuildingDTO buildingDTO);
    Optional<String> addTenant(TenantDTO tenantDTO);
    boolean addData(String jsonData, Date date);
}
