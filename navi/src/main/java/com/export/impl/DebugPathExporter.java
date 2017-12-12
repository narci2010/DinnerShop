package com.export.impl;

import com.export.interfaces.PathExporter;
import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.Deque;

public class DebugPathExporter implements PathExporter {
    private String toExport = "ShortestPath{";

    @Override
    public void fetchNodes(Deque<Node> nodes) {
        toExport += " nodes=[";
        StringBuilder stringBuilder = new StringBuilder(nodes.size() * 8);
        for (Node node : nodes) {
            stringBuilder.append(node.export(new DebugNodeExporter()));
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        toExport += stringBuilder.toString() + "]";

    }

    @Override
    public void fetchCost(Cost cost) {
        toExport += "cost=" + cost.toString() + ",";
    }

    @Override
    public String export() {
        return toExport += "}";
    }

}
