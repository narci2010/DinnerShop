package com.navigation;

import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Graph;
import com.graph.model.Node;

import java.util.*;
import java.util.function.Consumer;

public class RoadNetwork implements Graph<Node>, Iterable<Node> {

    private List<Node> nodes = new ArrayList<>(300000);
    private Map<Integer, Integer> nodePosition = new HashMap<>();


    @Override
    public void addNode(Node node) {
        if (!nodePosition.containsKey(node.getId())) {
            nodes.add(node);
            nodePosition.put(node.getId(), nodes.size() - 1);
        }

    }

    @Override
    public void addEdge(Node u, Node v, Cost cost) {


        nodes.get(nodePosition.get(u.getId())).addOutgoingArcs(new Arc(v, cost));
        nodes.get(nodePosition.get(v.getId())).addOutgoingArcs(new Arc(u, cost));


    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : nodes) {
            stringBuilder.append(node);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }


    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    @Override
    public void forEach(Consumer<? super Node> action) {
        nodes.forEach(action);
    }

    @Override
    public Spliterator<Node> spliterator() {
        return nodes.spliterator();
    }
}

