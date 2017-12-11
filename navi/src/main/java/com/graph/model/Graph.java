package com.graph.model;

public interface Graph<T extends Node> extends Iterable<T>{
    void addNode(T node);
    void addEdge(T u, T v, Cost cost);
    T findClosestNode(T node);
}
