package com.graph.algorithms;

import com.export.interfaces.Exportable;
import com.export.interfaces.PathExporter;
import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.Deque;

public class ShortestPath implements Exportable<PathExporter> {
    private Deque<Node> nodes;
    private Cost cost = new Cost(0);

    public ShortestPath(Deque<Node> nodes, Cost cost) {
        this.nodes = nodes;
        this.cost = cost;
    }

    public void addNodeToShortestPath(Node node) {
        nodes.push(node);
    }

    public Cost getCost() {
        return cost;
    }


    @Override
    public String export(PathExporter exporter) {
        exporter.fetchCost(this.cost);
        exporter.fetchNodes(this.nodes);
        return exporter.export();
    }
}
