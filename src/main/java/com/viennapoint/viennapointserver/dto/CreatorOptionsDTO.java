package com.viennapoint.viennapointserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorOptionsDTO {
    private int creatorOptionsEntityID;
    private Map<String, Object> creatorOptions;

    public int getCreatorOptionsEntityID() {
        return creatorOptionsEntityID;
    }

    public void setCreatorOptionsEntityID(int creatorOptionsEntityID) {
        this.creatorOptionsEntityID = creatorOptionsEntityID;
    }

    public Map<String, Object> getCreatorOptions() {
        return creatorOptions;
    }

    public void setCreatorOptions(Map<String, Object> creatorOptions) {
        this.creatorOptions = creatorOptions;
    }
}