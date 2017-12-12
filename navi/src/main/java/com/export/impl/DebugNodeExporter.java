package com.export.impl;

import com.export.interfaces.NodeExporter;
import com.graph.model.Arc;
import com.graph.model.Coordinate;

import java.util.List;

public class DebugNodeExporter implements NodeExporter {
    private String toExport = "Node";

    @Override
    public void fetchId(Integer id) {
        toExport += "{id=" + id + "}";
    }

    @Override
    public void fetchCoordinate(Coordinate coordinate) {

    }

    @Override
    public void fetchArcs(List<Arc> arcs) {

    }

    @Override
    public String export() {
        return toExport;
    }
}
