package com.viennapoint.viennapointserver.repository;

import com.viennapoint.viennapointserver.entity.ParsingOptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsingOptionsRepository extends JpaRepository<ParsingOptionsEntity, Integer> {
}