package com.graph;

public interface Graph {
    void addNode(Node node);
    void addEdge(Node u, Node v, Cost cost);
}
