package com.navigation;

import com.graph.Arc;
import com.graph.Cost;
import com.graph.Graph;
import com.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadNetwork implements Graph {

    private List<Node> nodes = new ArrayList<>();
    private Map<Node, List<Arc>> adjacentArcs = new HashMap<>();

    @Override
    public void addNode(Node node) {
        addNodeIfNotExist(node);

    }

    @Override
    public void addEdge(Node u, Node v, Cost cost) {

//        addNodeIfNotExist(u);
        addArc(u, v, cost);

//        addNodeIfNotExist(v);
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

    private void addNodeIfNotExist(Node node) {
     /*   if (!nodes.contains(node)) {
            nodes.add(node);
            adjacentArcs.add(new ArrayList<>());
        }*/
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Map<Node, List<Arc>> getAdjacentArcs() {
        return adjacentArcs;
    }
}

