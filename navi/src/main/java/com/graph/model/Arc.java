package com.graph.model;

public class Arc implements Comparable<Arc>{

    public Arc(Node headNode, Cost cost) {
        this.headNode = headNode;
        this.cost = cost;
    }

    private Node headNode;
    private Cost cost;


    public Node getHeadNode() {
        return headNode;
    }

    public Cost getCost() {
        return cost;
    }

    @Override
    public int compareTo(Arc o) {
        return this.cost.compareTo(o.cost);
    }

    @Override
    public String toString() {
        return "Arc{" +
                "headNodeId=" + headNode.getId() +
                "," + cost +
                '}';
    }

}
