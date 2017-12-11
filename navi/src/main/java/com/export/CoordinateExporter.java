package com.export;

import java.util.Map;

public class CoordinateExporter implements Exporter {
    @Override
    public String export(Map<String, String> propertiesValues) {
        return propertiesValues.get("latitude") + "," + propertiesValues.get("longitude");
    }
}
