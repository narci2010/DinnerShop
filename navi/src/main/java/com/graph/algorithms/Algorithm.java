package com.graph.algorithms;

import com.graph.model.Node;
import com.graph.weighting.Weighting;

public interface Algorithm {
    ShortestPath calculateShortestPath(Node sourceNode, Node targetNode, Weighting weighting);
}
