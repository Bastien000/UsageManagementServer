package com.viennapoint.viennapointserver.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantDTO {
    private int tenantEntityID;
    private String name;
    private Date moveInDate;
    private Date moveOutDate;

    private int buildingEntityID;
    private int floor;
    private double area;

    private Set<Integer> usageEntityID;
}
