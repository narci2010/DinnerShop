package com.dinner.model.value.objects.transfer;

import org.springframework.stereotype.Component;

@Component
public class JsonExporter implements Exporter {
    private String toExport = "{";

    @Override
    public void addFieldToExport(String filedName, String fieldValue) {
        toExport += "\"" + filedName + "\":\"" + fieldValue + "\",";
    }

    @Override
    public String toString() {
        toExport = toExport.substring(0, toExport.length() - 1);
        toExport += "}";
        return toExport;
    }

}
