package com.graph.algorithms;

import com.export.CoordinateExporter;
import com.export.Exportable;
import com.export.Exporter;
import com.export.NodeExporter;
import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ShortestPath implements Exportable {
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
    public String export(Exporter exporter) {
        Map<String, String> propertiesMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder(nodes.size()*4);

        propertiesMap.put("Cost", String.valueOf(cost));

        for (Node node : nodes) {
            stringBuilder.append(node.export(new NodeExporter()));
            stringBuilder.append(",");
        }


        return exporter.toString();
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ShortestPath{" + "cost=").append(cost).append(", nodes=[");
        for (Node node : nodes) {
            stringBuilder.append("Node{id=");
            stringBuilder.append(node.getId());
            stringBuilder.append("}, ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}
