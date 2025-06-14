package com.viennapoint.viennapointserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildingEntityID;
    private String name;
    private int floorCount;
    private double area;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "building_tenant_id", joinColumns = @JoinColumn(name = "buildingEntityID"))
    @Column(name = "tenantEntityID")
    private Set<Integer> tenantEntityID = new HashSet<>();

    // Gettery
    public int getBuildingEntityID() {
        return buildingEntityID;
    }

    public String getName() {
        return name;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public double getArea() {
        return area;
    }

    public Set<Integer> getTenantEntityID() {
        return tenantEntityID;
    }

    // Settery
    public void setBuildingEntityID(int buildingEntityID) {
        this.buildingEntityID = buildingEntityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setTenantEntityID(Set<Integer> tenantEntityID) {
        this.tenantEntityID = tenantEntityID;
    }

    // Původní metody
    public void addTenant(Integer ID) {
        this.tenantEntityID.add(ID);
    }

    public void deleteTenant(Integer ID) {
        this.tenantEntityID.remove(ID);
    }
}