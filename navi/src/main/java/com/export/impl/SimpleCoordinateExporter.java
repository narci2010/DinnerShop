package com.export.impl;

import com.export.interfaces.CoordinateExporter;

public class SimpleCoordinateExporter implements CoordinateExporter {
    private String toExport="";

    @Override
    public void fetchLon(double lon) {
        if (toExport.isEmpty())
            toExport += String.valueOf(lon);
        else {
            toExport += "," + String.valueOf(lon);
        }
    }

    @Override
    public void fetchLan(double lan) {
        if (toExport.isEmpty())
            toExport += String.valueOf(lan);
        else {
            toExport += "," + String.valueOf(lan);
        }
    }

    @Override
    public String export() {
        return toExport;
    }

}
