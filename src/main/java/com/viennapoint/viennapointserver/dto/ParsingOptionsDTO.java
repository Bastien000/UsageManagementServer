package com.viennapoint.viennapointserver.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsingOptionsDTO {
    private int parsingOptionsEntityID;
    private Map<String, List<String>> remapingOptions;
    private List<Map<String, Double>> splitOptions;

    public int getParsingOptionsEntityID() {
        return parsingOptionsEntityID;
    }

    public void setParsingOptionsEntityID(int parsingOptionsEntityID) {
        this.parsingOptionsEntityID = parsingOptionsEntityID;
    }

    public Map<String, List<String>> getRemapingOptions() {
        return remapingOptions;
    }

    public void setRemapingOptions(Map<String, List<String>> remapingOptions) {
        this.remapingOptions = remapingOptions;
    }

    public List<Map<String, Double>> getSplitOptions() {
        return splitOptions;
    }

    public void setSplitOptions(List<Map<String, Double>> splitOptions) {
        this.splitOptions = splitOptions;
    }
}