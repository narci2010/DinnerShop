package com.export;

import java.util.Map;

public interface Exporter {
    String export(Map<String, String> propertiesValues);
}
