package com.viennapoint.viennapointserver.repository;

import com.viennapoint.viennapointserver.entity.CreatorOptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorOptionsRepository extends JpaRepository<CreatorOptionsEntity, Integer> {
}