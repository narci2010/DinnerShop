package com.graph.algorithms;

import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.Deque;

public class ShortestPath {
    private Deque<Node> nodes;
    private Cost cost = new Cost(0);

    public ShortestPath(Deque<Node> nodes, Cost cost) {
        this.nodes = nodes;
        this.cost = cost;
    }

    public void addNodeToShortestPath(Node node){
        nodes.push(node);
    }

    @Override
    public String toString() {
        return "ShortestPath{" +
                "cost=" + cost +
                ", nodes=" + nodes +
                '}';
    }
}
