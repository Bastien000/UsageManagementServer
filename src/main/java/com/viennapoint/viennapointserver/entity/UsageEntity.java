package com.viennapoint.viennapointserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class UsageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usageEntityID;
    private int tenantEntityID;
    private String type;
    private double value;
    private Date accountingMonth;

    // Gettery
    public int getUsageEntityID() {
        return usageEntityID;
    }

    public int getTenantEntityID() {
        return tenantEntityID;
    }

    public double getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public Date getAccountingMonth() {
        return accountingMonth;
    }

    // Settery
    public void setUsageEntityID(int usageEntityID) {
        this.usageEntityID = usageEntityID;
    }

    public void setTenantEntityID(int tenantEntityID) {
        this.tenantEntityID = tenantEntityID;
    }



    public void setAccountingMonth(Date accountingMonth) {
        this.accountingMonth = accountingMonth;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }
}