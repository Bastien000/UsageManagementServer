package com.viennapoint.viennapointserver.repository;

import com.viennapoint.viennapointserver.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TenantRepository  extends JpaRepository<TenantEntity, Long>, JpaSpecificationExecutor<TenantEntity> {
    Optional<TenantEntity> findByName(String name);

    @Query("SELECT t, GROUP_CONCAT(CASE WHEN MONTH(u.accountingMonth) = :month AND YEAR(u.accountingMonth) = :year THEN u.usageEntityID ELSE NULL END) as usageEntityIDs " +
            "FROM TenantEntity t " +
            "LEFT JOIN UsageEntity u ON t.tenantEntityID = u.tenantEntityID " +
            "WHERE EXISTS (SELECT 1 FROM UsageEntity ue WHERE ue.tenantEntityID = t.tenantEntityID AND MONTH(ue.accountingMonth) = :month AND YEAR(ue.accountingMonth) = :year) " +

            "AND t.buildingEntityID = :buildingID " +

            "GROUP BY t.tenantEntityID, t.name, t.area, t.buildingEntityID, t.floor, t.moveInDate, t.moveOutDate")
    List<Object[]> findTenantsWithUsageInMonth(@Param("month") int month, @Param("year") int year, @Param("buildingID") Long buildingID );

}