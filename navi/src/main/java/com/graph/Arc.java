package com.graph;

public class Arc {

    public Arc(Node headNode, Cost cost) {
        this.headNode = headNode;
        this.cost = cost;
    }

    private Node headNode;
    private Cost cost;

}
