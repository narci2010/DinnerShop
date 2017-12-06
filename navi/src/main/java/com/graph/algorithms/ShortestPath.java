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

    public Cost getCost() {
        return cost;
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
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}
