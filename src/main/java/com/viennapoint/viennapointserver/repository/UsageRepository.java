package com.viennapoint.viennapointserver.repository;

import com.viennapoint.viennapointserver.entity.TenantEntity;
import com.viennapoint.viennapointserver.entity.UsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsageRepository  extends JpaRepository<UsageEntity, Long>, JpaSpecificationExecutor<UsageEntity> {


}