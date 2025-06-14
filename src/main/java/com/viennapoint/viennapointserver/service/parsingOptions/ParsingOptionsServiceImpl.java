package com.viennapoint.viennapointserver.service.parsingOptions;

import com.viennapoint.viennapointserver.dto.mapper.ParsingOptionsMapper;
import com.viennapoint.viennapointserver.dto.ParsingOptionsDTO;
import com.viennapoint.viennapointserver.entity.ParsingOptionsEntity;
import com.viennapoint.viennapointserver.repository.ParsingOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParsingOptionsServiceImpl implements ParsingOptionsService {

    @Autowired
    private ParsingOptionsRepository repository;



    @Override
    public String save(ParsingOptionsDTO dto) {
        ParsingOptionsEntity entity = ParsingOptionsMapper.toEntity(dto);
        repository.save(entity);
        return "Entity saved";
    }

    @Override
    public ParsingOptionsDTO findById(int id) {
        return repository.findById(id)
                .map(ParsingOptionsMapper::toDTO)
                .orElse(null);
    }
}
