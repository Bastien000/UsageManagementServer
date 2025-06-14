package com.viennapoint.viennapointserver.service.parsingOptions;

import com.viennapoint.viennapointserver.dto.ParsingOptionsDTO;

public interface ParsingOptionsService {
    String save(ParsingOptionsDTO dto);

    ParsingOptionsDTO findById(int id);
}
