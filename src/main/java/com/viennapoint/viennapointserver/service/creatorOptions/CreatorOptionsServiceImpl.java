package com.viennapoint.viennapointserver.service.creatorOptions;

import com.viennapoint.viennapointserver.dto.mapper.CreatorOptionsMapper;
import com.viennapoint.viennapointserver.dto.CreatorOptionsDTO;
import com.viennapoint.viennapointserver.entity.CreatorOptionsEntity;
import com.viennapoint.viennapointserver.repository.CreatorOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatorOptionsServiceImpl implements CreatorOptionsService {

    @Autowired
    private CreatorOptionsRepository repository;

    @Override
    public String save(CreatorOptionsDTO dto) {
        CreatorOptionsEntity entity = CreatorOptionsMapper.toEntity(dto);
        System.out.println(entity.getCreatorOptionsJSON());
        repository.save(entity);
        return "Entity saved";
    }

    @Override
    public CreatorOptionsDTO findById(int id) {
        return repository.findById(id)
                .map(CreatorOptionsMapper::toDTO)
                .orElse(null);
    }
}