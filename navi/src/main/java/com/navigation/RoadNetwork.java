package com.navigation;

import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Graph;
import com.graph.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadNetwork implements Graph {

    private List<Node> nodes = new ArrayList<>();
    private Map<Node, List<Arc>> adjacentArcs = new HashMap<>();

    @Override
    public void addNode(Node node) {
        nodes.add(node);

    }

    @Override
    public void addEdge(Node u, Node v, Cost cost) {

        addArc(u, v, cost);
        addArc(v, u, cost);

    }

    private void addArc(Node tailNode, Node headNode, Cost cost) {

        if (adjacentArcs.containsKey(tailNode)) {
            adjacentArcs.get(tailNode).add(new Arc(headNode, cost));
        } else {
            List<Arc> arcs = new ArrayList<>();
            arcs.add(new Arc(headNode, cost));
            adjacentArcs.put(tailNode, arcs);
        }

    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Map<Node, List<Arc>> getAdjacentArcs() {
        return adjacentArcs;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Node, List<Arc>> nodeListEntry : adjacentArcs.entrySet()) {
            stringBuilder.append(nodeListEntry.getKey());
            stringBuilder.append(" : ");
            stringBuilder.append(nodeListEntry.getValue());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}

