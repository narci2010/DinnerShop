package com.export.interfaces;

public interface CoordinateExporter extends Exporter {
    void fetchLon(double lon);
    void fetchLan(double lan);
}
