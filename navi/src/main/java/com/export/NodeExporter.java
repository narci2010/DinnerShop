package com.export;

import java.util.Map;

public class NodeExporter implements Exporter {

    @Override
    public String export(Map<String, String> propertiesValues) {
        return "Node{" +
                "coordinate=" + propertiesValues.get("coordinate") + "\n" +
                "id=" + propertiesValues.get("id") + "\n"+
                "} : " + propertiesValues.get("outgoingArcs");
    }
}
