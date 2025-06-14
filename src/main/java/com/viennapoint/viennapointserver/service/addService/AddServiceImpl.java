package com.viennapoint.viennapointserver.service.addService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viennapoint.viennapointserver.dto.BuildingDTO;
import com.viennapoint.viennapointserver.dto.TenantDTO;
import com.viennapoint.viennapointserver.dto.mapper.BuildingMapper;
import com.viennapoint.viennapointserver.dto.mapper.TenantMapper;
import com.viennapoint.viennapointserver.dto.mapper.UsageMapper;
import com.viennapoint.viennapointserver.entity.BuildingEntity;
import com.viennapoint.viennapointserver.entity.TenantEntity;
import com.viennapoint.viennapointserver.entity.UsageEntity;
import com.viennapoint.viennapointserver.repository.BuildingRepository;
import com.viennapoint.viennapointserver.repository.TenantRepository;
import com.viennapoint.viennapointserver.repository.UsageRepository;
import com.viennapoint.viennapointserver.service.getService.GetService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AddServiceImpl  implements  AddService{


    private final GetService getService;
    private  final BuildingMapper buildingMapper;
    private  final BuildingRepository buildingRepository;
    private  final TenantRepository tenantRepository;
    private  final TenantMapper tenantMapper;
    private  final UsageRepository usageRepository;
    private  final UsageMapper usageMapper;
    public AddServiceImpl(GetService getService, BuildingMapper buildingMapper, BuildingRepository buildingRepository, TenantRepository tenantRepository, TenantMapper tenantMapper, UsageRepository usageRepository, UsageMapper usageMapper) {
        this.getService = getService;
        this.buildingMapper = buildingMapper;
        this.buildingRepository = buildingRepository;
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.usageRepository = usageRepository;
        this.usageMapper = usageMapper;
    }

    private Map<String, Double> convertJsonStringToMap(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Map<String, Double>> typeRef = new TypeReference<>() {};
            return mapper.readValue(jsonString, typeRef);
        } catch (JsonProcessingException e) {
            System.err.println("Chyba při převodu JSON na Map: " + e.getMessage());
            return new HashMap<>();
        }
    }

    @Override
    public Optional<String> addBuilding(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = buildingMapper.toEntity(buildingDTO);
        if(getService.getBuildingsNameAndID().containsValue(buildingEntity.getName()) && !getService.getBuildingsNameAndID().get(buildingEntity.getBuildingEntityID()).equals(buildingEntity.getName())){
            return "Building already exist".describeConstable();
        }
        buildingRepository.save(buildingEntity);
        return Optional.empty();
    }

    @Override
    public Optional<String> addTenant(TenantDTO tenantDTO){
        TenantEntity tenantEntity = tenantMapper.toEntity(tenantDTO);
        if(getService.getTenantsNameAndID().containsValue(tenantEntity.getName()) && !getService.getTenantsNameAndID().get(tenantEntity.getTenantEntityID()).equals(tenantEntity.getName())){
            return "Tenant already exist".describeConstable();
        }
        if(!getService.getBuildingsID().contains(tenantEntity.getBuildingEntityID())){
            return "Building do not exist".describeConstable();
        }

        tenantEntity = tenantRepository.save(tenantEntity);
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(Long.valueOf(tenantEntity.getBuildingEntityID()));
        if(buildingEntity.isEmpty()){
            return "Building do not exist".describeConstable();
        }
        buildingEntity.get().addTenant(tenantEntity.getTenantEntityID());
        buildingRepository.save(buildingEntity.get());
        return Optional.empty();
    }


    @Override
    public boolean addData(String jsonData, Date date){
        Map<String, Double> data = convertJsonStringToMap(jsonData);
        if(data.isEmpty()) {
            return false;
        }
        for (String name : data.keySet()){
            Optional<TenantEntity> tenantEntity = tenantRepository.findByName(name);
            UsageEntity usageEntity = new UsageEntity();
            usageEntity.setType("electricity");
            usageEntity.setAccountingMonth(date);
            usageEntity.setValue(Math.round(data.get(name) * 100.0) / 100.0);
            if(tenantEntity.isPresent()){
                for (int usageID : tenantEntity.get().getUsageEntityID()){
                    Optional<UsageEntity> usage = usageRepository.findById(Long.valueOf(usageID));
                    if(usage.isPresent()){
                        if(usage.get().getAccountingMonth().getMonth() == usageEntity.getAccountingMonth().getMonth() && usage.get().getAccountingMonth().getYear() == usageEntity.getAccountingMonth().getYear()){
                            usageEntity.setUsageEntityID(usageID);
                        }
                    }
                }
                usageEntity.setTenantEntityID(tenantEntity.get().getTenantEntityID());
                usageEntity = usageRepository.save(usageEntity);
                tenantEntity.get().addUsage(usageEntity.getUsageEntityID());
                tenantRepository.save(tenantEntity.get());

            }
            else {
                TenantEntity newTenant = new TenantEntity();
                newTenant.setName(name);
                newTenant.setMoveInDate(new Date());
                newTenant = tenantRepository.save(newTenant);
                usageEntity.setTenantEntityID(newTenant.getTenantEntityID());
                usageEntity = usageRepository.save(usageEntity);
                newTenant.addUsage(usageEntity.getUsageEntityID());
                tenantRepository.save(newTenant);
            }
        }
        return true;

    }



}
