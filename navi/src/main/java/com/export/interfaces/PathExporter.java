package com.export.interfaces;

import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.Deque;

public interface PathExporter extends Exporter {
    void fetchNodes(Deque<Node> nodes);
    void fetchCost(Cost cost);
}
