package com.viennapoint.viennapointserver.service.creatorOptions;

import com.viennapoint.viennapointserver.dto.CreatorOptionsDTO;

public interface CreatorOptionsService {
    String save(CreatorOptionsDTO dto);
    CreatorOptionsDTO findById(int id);
}