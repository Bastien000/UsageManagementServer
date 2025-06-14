package com.viennapoint.viennapointserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tenantEntityID;
    private String name;
    private Date moveInDate;
    private Date moveOutDate;
    private int buildingEntityID;
    private int floor;
    private double area;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tenant_usage_id", joinColumns = @JoinColumn(name = "tenantEntityID"))
    @Column(name = "usageEntityID")
    private Set<Integer> usageEntityID = new HashSet<>();

    // Gettery

    public int getTenantEntityID() {
        return tenantEntityID;
    }

    public String getName() {
        return name;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public Date getMoveOutDate() {
        return moveOutDate;
    }

    public int getBuildingEntityID() {
        return buildingEntityID;
    }

    public int getFloor() {
        return floor;
    }

    public double getArea() {
        return area;
    }

    public Set<Integer> getUsageEntityID() {
        return usageEntityID;
    }

    // Settery
    public void setTenantEntityID(int tenantEntityID) {
        this.tenantEntityID = tenantEntityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public void setMoveOutDate(Date moveOutDate) {
        this.moveOutDate = moveOutDate;
    }

    public void setBuildingEntityID(int buildingEntityID) {
        this.buildingEntityID = buildingEntityID;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setUsageEntityID(Set<Integer> usageEntityID) {
        this.usageEntityID = usageEntityID;
    }

    // Původní metody
    public void addUsage(Integer ID) {
        this.usageEntityID.add(ID);
    }

    public void deleteUsage(Integer ID) {
        this.usageEntityID.remove(ID);
    }
}