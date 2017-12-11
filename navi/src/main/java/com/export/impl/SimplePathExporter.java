package com.export.impl;

import com.export.interfaces.PathExporter;
import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.Deque;

public class SimplePathExporter implements PathExporter {
    private String toExport = "";

    @Override
    public void fetchNodes(Deque<Node> nodes) {
        StringBuilder stringBuilder = new StringBuilder(nodes.size() * 8);
        for (Node node : nodes) {
            stringBuilder.append(node.export(new SimpleNodeExporter()));
            stringBuilder.append(",");
        }

        if (stringBuilder.length() > 2) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        toExport = stringBuilder.toString();

    }

    @Override
    public void fetchCost(Cost cost) {

    }

    @Override
    public String export() {
        return toExport;
    }
}
