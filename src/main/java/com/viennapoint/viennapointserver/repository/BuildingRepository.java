package com.viennapoint.viennapointserver.repository;

import com.viennapoint.viennapointserver.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository  extends JpaRepository<BuildingEntity, Long>, JpaSpecificationExecutor<BuildingEntity> {


}