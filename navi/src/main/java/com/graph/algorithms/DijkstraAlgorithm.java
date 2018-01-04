package com.graph.algorithms;

import com.graph.model.Node;
import com.graph.weighting.Weighting;

public interface DijkstraAlgorithm extends Algorithm {

    void calculateShortestPathsFromSource(Node sourceNode, Weighting weighting);

    ShortestPath readPathTo(Node targetNode);
}
