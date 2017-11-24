package com.graph.algorithms;

import com.graph.model.Cost;
import com.graph.model.Node;

import java.util.List;

public class ShortestPath {
    List<Node> nodes;
    Cost cost = new Cost(0);

    public void addNodeToShortestPath(Node node){
        nodes.add(node);

        //Todo add cost
        cost.addCost(null);
    }
}
