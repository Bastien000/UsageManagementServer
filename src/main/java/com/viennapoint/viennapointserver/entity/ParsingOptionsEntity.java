package com.viennapoint.viennapointserver.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Data
@Entity
public class ParsingOptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parsingOptionsEntityID;

    @Column(columnDefinition = "JSON")
    private String remapingOptionsJSON;

    @Column(columnDefinition = "JSON")
    private String splitOptionsJSON;

    public int getParsingOptionsEntityID() {
        return parsingOptionsEntityID;
    }

    public String getRemapingOptionsJSON() {
        return remapingOptionsJSON;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public String getSplitOptionsJSON() {
        return splitOptionsJSON;
    }

    public void setSplitOptionsJSON(String splitOptionsJSON) {
        this.splitOptionsJSON = splitOptionsJSON;
    }

    public void setParsingOptionsEntityID(int parsingOptionsEntityID) {
        this.parsingOptionsEntityID = parsingOptionsEntityID;
    }

    public void setRemapingOptionsJSON(String remapingOptionsJSON) {
        this.remapingOptionsJSON = remapingOptionsJSON;
    }

    // Helper methods for JSON handling
    private static final ObjectMapper mapper = new ObjectMapper();

    public Map<String, List<String>> getRemapingOptions() throws Exception {
        return mapper.readValue(remapingOptionsJSON, new TypeReference<Map<String, List<String>>>() {});
    }

    public void setRemapingOptions(Map<String, List<String>> options) throws Exception {
        this.remapingOptionsJSON = mapper.writeValueAsString(options);
    }

    public List<Map<String, Double>> getSplitOptions() throws Exception {
        return mapper.readValue(splitOptionsJSON, new TypeReference<List<Map<String, Double>>>() {});
    }

    public void setSplitOptions(List<Map<String, Double>> options) throws Exception {
        this.splitOptionsJSON = mapper.writeValueAsString(options);
    }
}