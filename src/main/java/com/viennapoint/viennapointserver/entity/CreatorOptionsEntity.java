package com.viennapoint.viennapointserver.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Data
@Entity
public class CreatorOptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int creatorOptionsEntityID;

    @Column(columnDefinition = "JSON")
    private String creatorOptionsJSON;

    // Helper methods for JSON handling
    private static final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Object> getCreatorOptions() throws Exception {
        return mapper.readValue(creatorOptionsJSON, new TypeReference<Map<String, Object>>() {});
    }

    public void setCreatorOptions(Map<String, Object> options) throws Exception {
        this.creatorOptionsJSON = mapper.writeValueAsString(options);
    }

    public int getCreatorOptionsEntityID() {
        return creatorOptionsEntityID;
    }

    public void setCreatorOptionsEntityID(int creatorOptionsEntityID) {
        this.creatorOptionsEntityID = creatorOptionsEntityID;
    }

    public String getCreatorOptionsJSON() {
        return creatorOptionsJSON;
    }

    public void setCreatorOptionsJSON(String creatorOptionsJSON) {
        this.creatorOptionsJSON = creatorOptionsJSON;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}