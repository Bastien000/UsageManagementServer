package com.viennapoint.viennapointserver.service.getService;

import com.viennapoint.viennapointserver.entity.BuildingEntity;
import com.viennapoint.viennapointserver.entity.TenantEntity;
import com.viennapoint.viennapointserver.repository.BuildingRepository;
import com.viennapoint.viennapointserver.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetServiceImpl implements  GetService{

    final BuildingRepository buildingRepository;
    final TenantRepository tenantRepository;

    public GetServiceImpl(BuildingRepository buildingRepository, TenantRepository tenantRepository) {
        this.buildingRepository = buildingRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<String> getBuildingsName(){
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        List<String> names = new ArrayList<String>();
        for (BuildingEntity entity : buildingEntities){
            names.add(entity.getName());
        }
        return names;
    }

    @Override
    public Map<Integer , String> getBuildingsNameAndID(){
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        Map<Integer, String> names = new HashMap<>();
        for (BuildingEntity entity : buildingEntities){
            names.put(entity.getBuildingEntityID(), entity.getName());
        }
        return names;
    }


    @Override
    public List<String> getTenantsName(){
        List<TenantEntity> tenantEntities = tenantRepository.findAll();
        List<String> names = new ArrayList<String>();
        for (TenantEntity entity : tenantEntities){
            names.add(entity.getName());
        }
        return names;
    }

    @Override
    public Map<Integer , String> getTenantsNameAndID(){
        List<TenantEntity> tenantEntities = tenantRepository.findAll();
        Map<Integer, String> names = new HashMap<>();
        for (TenantEntity entity : tenantEntities){
            names.put(entity.getTenantEntityID(), entity.getName());
        }
        return names;
    }

    @Override
    public List<Integer> getBuildingsID(){
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        List<Integer> ids = new ArrayList<Integer>();
        for (BuildingEntity entity : buildingEntities){
            ids.add(entity.getBuildingEntityID());
        }
        return ids;
    }

}
