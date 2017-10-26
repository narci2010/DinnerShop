package com.dinner.model.value.objects.transfer;

import com.dinner.annotations.domain.ValueObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@ValueObject
public interface Exporter {
    void addFieldToExport(String filedName, String fieldValue);

    default boolean isValid(String json) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
