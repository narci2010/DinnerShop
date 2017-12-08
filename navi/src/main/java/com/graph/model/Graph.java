package com.graph.model;

public interface Graph<T extends Node> extends Iterable<T>{
    void addNode(T node);
    void addEdge(Node u, Node v, Cost cost);
    Node findClosestNode(Coordinate coordinate);
}
