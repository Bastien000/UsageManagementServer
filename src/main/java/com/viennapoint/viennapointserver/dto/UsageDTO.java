package com.viennapoint.viennapointserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsageDTO {
    private int UsageEntityID;

    private int tenantEntityID;
    private double value;
    private String type;
    private Date accountingMonth;
}
