package com.viennapoint.viennapointserver.controller;

import com.viennapoint.viennapointserver.dto.*;
import com.viennapoint.viennapointserver.dto.mapper.BuildingMapper;
import com.viennapoint.viennapointserver.dto.mapper.TenantMapper;
import com.viennapoint.viennapointserver.dto.mapper.UsageMapper;
import com.viennapoint.viennapointserver.entity.BuildingEntity;
import com.viennapoint.viennapointserver.entity.TenantEntity;
import com.viennapoint.viennapointserver.repository.BuildingRepository;
import com.viennapoint.viennapointserver.repository.TenantRepository;
import com.viennapoint.viennapointserver.repository.UsageRepository;
import com.viennapoint.viennapointserver.service.addService.AddService;
import com.viennapoint.viennapointserver.service.creatorOptions.CreatorOptionsService;
import com.viennapoint.viennapointserver.service.parsingOptions.ParsingOptionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AddController {


    final AddService addService;
    final BuildingRepository buildingRepository;
    final BuildingMapper buildingMapper;
    final UsageRepository usageRepository;
    final UsageMapper usageMapper;
    final TenantRepository tenantRepository;
    final TenantMapper tenantMapper;
    final ParsingOptionsService parsingOptionsService;
    final CreatorOptionsService creatorOptionsService;

    public AddController(AddService addService, BuildingRepository buildingRepository, BuildingMapper buildingMapper, UsageRepository usageRepository, UsageMapper usageMapper, TenantRepository tenantRepository, TenantMapper tenantMapper, ParsingOptionsService parsingOptionsService, CreatorOptionsService creatorOptionsService) {
        this.addService = addService;
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
        this.usageRepository = usageRepository;
        this.usageMapper = usageMapper;
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.parsingOptionsService = parsingOptionsService;
        this.creatorOptionsService = creatorOptionsService;
    }

    @PostMapping("/addBuilding")
    public ResponseEntity<String> addBuilding(@RequestBody BuildingDTO buildingDTO){
        System.out.println("Add building");
        Optional<String> rt = addService.addBuilding(buildingDTO);
        if(rt.isEmpty()){
            return ResponseEntity.ok("Building added");
        }
        return ResponseEntity.badRequest().body(rt.get());
    }

    @PostMapping("/addUsage/{year}/{month}")

    public Object addUsage(@RequestBody String usageJson, @PathVariable int year, @PathVariable int month){
        System.out.println("Add usage");
        Date date = new Date(year - 1900, month , 1);
        boolean rt = addService.addData(usageJson, date);
        if(rt){
            return ResponseEntity.ok("Usage added");
        }
        return ResponseEntity.badRequest();
    }

    @PostMapping("/addTenant")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO){
        System.out.println("Add tenant");
        Optional<String> rt = addService.addTenant(tenantDTO);
        if(rt.isEmpty()){
            return ResponseEntity.ok("Tenant added");
        }
        return ResponseEntity.badRequest().body(rt.get());
    }

    @PostMapping("addParsingOptions")
    public ResponseEntity<String> addParsingOptions(@RequestBody ParsingOptionsDTO dto) throws Exception {
        String result = parsingOptionsService.save(dto);
        System.out.println("add parsing options");
        return ResponseEntity.ok(result);
    }

    @PostMapping("addCreatorOptions")
    public ResponseEntity<String> addCreatorOptions(@RequestBody CreatorOptionsDTO dto) throws Exception {
        String result = creatorOptionsService.save(dto);
        System.out.println("add creator options");
        return ResponseEntity.ok(result);
    }

    @GetMapping("removeBuilding/{ID}")
    public Object removeBuilding(@PathVariable int ID){
        System.out.println("Remove building by ID: " + ID);
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById((long) ID);
        if(buildingEntity.isPresent()){
            for (int tenantID : buildingEntity.get().getTenantEntityID()){
                TenantEntity tenantEntity = tenantRepository.findById((long) tenantID).get();
                tenantEntity.setBuildingEntityID(0);
                tenantRepository.save(tenantEntity);
            }
            buildingRepository.deleteById((long) ID);
            return ResponseEntity.ok("");
        }
        return ResponseEntity.badRequest();
    }
    @GetMapping("removeTenant/{ID}")
    public Object removeTenant(@PathVariable int ID){
        System.out.println("Remove tenant by ID: " + ID);
        Optional<TenantEntity> tenantEntity = tenantRepository.findById((long) ID);
        if(tenantEntity.isPresent()){
            BuildingEntity buildingEntity = buildingRepository.findById((long) tenantEntity.get().getBuildingEntityID()).get();
            buildingEntity.deleteTenant(ID);
            buildingRepository.save(buildingEntity);
            tenantRepository.deleteById((long) ID);
            return ResponseEntity.ok("");

        }
        return ResponseEntity.badRequest();

    }
}
