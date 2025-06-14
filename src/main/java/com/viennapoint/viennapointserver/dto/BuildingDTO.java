package com.viennapoint.viennapointserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {

    private int buildingEntityID;
    private String name;
    private int floorCount;
    private double area;
    private Set<Integer> tenantEntityID ;
}
