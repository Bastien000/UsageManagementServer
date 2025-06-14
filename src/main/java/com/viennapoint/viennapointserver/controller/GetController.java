package com.viennapoint.viennapointserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viennapoint.viennapointserver.dto.*;
import com.viennapoint.viennapointserver.dto.mapper.BuildingMapper;
import com.viennapoint.viennapointserver.dto.mapper.TenantMapper;
import com.viennapoint.viennapointserver.dto.mapper.UsageMapper;
import com.viennapoint.viennapointserver.entity.BuildingEntity;
import com.viennapoint.viennapointserver.entity.TenantEntity;
import com.viennapoint.viennapointserver.entity.UsageEntity;
import com.viennapoint.viennapointserver.repository.BuildingRepository;
import com.viennapoint.viennapointserver.repository.TenantRepository;
import com.viennapoint.viennapointserver.repository.UsageRepository;
import com.viennapoint.viennapointserver.service.creatorOptions.CreatorOptionsService;
import com.viennapoint.viennapointserver.service.getService.GetService;
import com.viennapoint.viennapointserver.service.parsingOptions.ParsingOptionsService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class GetController {

    final GetService getService;
    final BuildingRepository buildingRepository;
    final BuildingMapper buildingMapper;
    final UsageRepository usageRepository;
    final UsageMapper usageMapper;
    final TenantRepository tenantRepository;
    final TenantMapper tenantMapper;
    final ParsingOptionsService parsingOptionsService;
    final CreatorOptionsService creatorOptionsService;


    public GetController(GetService getService, BuildingRepository buildingRepository, BuildingMapper buildingMapper, UsageRepository usageRepository, UsageMapper usageMapper, TenantRepository tenantRepository, TenantMapper tenantMapper, ParsingOptionsService parsingOptionsService, CreatorOptionsService creatorOptionsService) {
        this.getService = getService;
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
        this.usageRepository = usageRepository;
        this.usageMapper = usageMapper;
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.parsingOptionsService = parsingOptionsService;
        this.creatorOptionsService = creatorOptionsService;
    }


    @GetMapping("/getBuildings")
    public List<BuildingDTO> getBuildings() {
        System.out.println("Get buildings");
        return buildingMapper.toDTO( buildingRepository.findAll());
    }
    @GetMapping("/getTenants")
    public List<TenantDTO> getTenants() {
        System.out.println("Get tenants");
        return tenantMapper.toDTO(tenantRepository.findAll());
    }
    @GetMapping("/getUsages")
    public List<UsageDTO> getUsage() {
        System.out.println("Get usage");
        return usageMapper.toDTO(usageRepository.findAll());
    }
    @GetMapping("/getBuildingsName")
    public Map<Integer, String> getBuildingsName(){
        System.out.println("Get buildings name");
        return  getService.getBuildingsNameAndID();
    }
    @GetMapping("/getTenantsName")
    public Map<Integer, String> getTenantsName(){
        System.out.println("Get tenants name");
        return  getService.getTenantsNameAndID();
    }

    @GetMapping("/getTenant/{ID}")
    public ResponseEntity<TenantDTO> getTenants(@PathVariable int ID) {
        System.out.println("Get tenant by ID: " + ID);
        Optional<TenantEntity> tenantEntity = tenantRepository.findById((long) ID);
        if(tenantEntity.isEmpty()){
            return  ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(tenantMapper.toDTO(tenantEntity.get()));
    }

    @GetMapping("getUsage/{ID}")
    public ResponseEntity<UsageDTO> getUsage(@PathVariable int ID){
        System.out.println("Get usage by ID: " + ID);
        Optional<UsageEntity> usageEntity = usageRepository.findById((long) ID);
        if(usageEntity.isEmpty()){
            return  ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(usageMapper.toDTO(usageEntity.get()));
    }

    @GetMapping("getBuilding/{ID}")
    public ResponseEntity<BuildingDTO> getBuilding(@PathVariable int ID){
        System.out.println("Get building by ID: " + ID);
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById((long) ID);
        if(buildingEntity.isEmpty()){
            return  ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(buildingMapper.toDTO(buildingEntity.get()));
    }

    @PostMapping("getTenantsByUsageDate/{year}/{month}")
    public ResponseEntity<InputStreamResource> getTenantsByUsageDate(@PathVariable int month, @PathVariable int year, @RequestBody List<Integer> buildingsID) throws IOException, InterruptedException, URISyntaxException {
        List<Object[]> tenantsData = new ArrayList<>();
        for (Integer ID : buildingsID){
            List<Object[]> tenants = tenantRepository.findTenantsWithUsageInMonth(month, year, Long.valueOf(ID));
            tenantsData.addAll(tenants);
        }


        String jsonData = new ObjectMapper().writeValueAsString(tenantsData);

        // Vytvoření HTTP požadavku
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://192.168.1.137:5000/getExcel"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonData, StandardCharsets.UTF_8))
                .build();

        // Odeslání požadavku a získání odpovědi
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        // Kontrola odpovědi
        if (response.statusCode() == 200 && response.body() != null) {
            // Vytvoření InputStreamResource z byte array
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(response.body()));

            // Nastavení hlaviček
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tenant_data.xlsx");
            headers.setContentType(MediaType.parseMediaType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }
    @GetMapping("getParsingOption/{id}")
    public ResponseEntity<ParsingOptionsDTO> getParsingOption(@PathVariable int id) {
        ParsingOptionsDTO dto = parsingOptionsService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("getCreatorOption/{id}")
    public ResponseEntity<CreatorOptionsDTO> getCreatorOption(@PathVariable int id) {
        CreatorOptionsDTO dto = creatorOptionsService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

}