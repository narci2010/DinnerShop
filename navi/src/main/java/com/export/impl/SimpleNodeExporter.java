package com.export.impl;

import com.export.interfaces.NodeExporter;
import com.graph.model.Arc;
import com.graph.model.Coordinate;

import java.util.List;

public class SimpleNodeExporter implements NodeExporter {

    private String toExport="";

    @Override
    public void fetchId(Integer id) {

    }

    @Override
    public void fetchCoordinate(Coordinate coordinate) {
        toExport = coordinate.export(new SimpleCoordinateExporter());
    }

    @Override
    public void fetchArcs(List<Arc> arcs) {

    }

    @Override
    public String export() {
        return toExport;
    }
}
