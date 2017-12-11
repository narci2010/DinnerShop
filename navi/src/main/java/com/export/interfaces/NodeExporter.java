package com.export.interfaces;

import com.graph.model.Arc;
import com.graph.model.Coordinate;

import java.util.List;

public interface NodeExporter extends Exporter {
    void fetchId(Integer id);
    void fetchCoordinate(Coordinate coordinate);
    void fetchArcs(List<Arc> arcs);
}
