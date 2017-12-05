package com.graph.algorithms.dijkstra.astar;

import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.algorithms.dijkstra.astar.heuristic.RandomLandmarkSelection;
import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;

import java.util.*;

public class AStar extends DijkstraAlgorithm {

    private Map<Node, Map<Node, Cost>> distanceFromLandmarkToNodes = new HashMap<>();
    private RandomLandmarkSelection landmarkSelection;

    public AStar(RoadNetwork roadNetwork, RandomLandmarkSelection landmarkSelection) {
        super(roadNetwork);
        this.landmarkSelection = landmarkSelection;
    }

    public void precomputeDistances(Integer numberOfLandmarks) {
        distanceFromLandmarkToNodes = landmarkSelection.precomputeDistances(numberOfLandmarks);
    }

    @Override
    protected void addToUnsettledNodes(Arc arc) {

//        unsettledNodes.offer(new Arc(arc.getHeadNode(), tailNode, arc.getCost().addCost(calculateHeuristic(arc.getHeadNode()))));
    }


    private Cost calculateHeuristic(Node currentNode) {
        Cost maxCost = new Cost(0);
        for (Node landmark : distanceFromLandmarkToNodes.keySet()) {
            Map<Node, Cost> nodeCostMap = distanceFromLandmarkToNodes.get(landmark);
            Cost distanceFromLandmarkToCurrentNode = nodeCostMap.get(currentNode);
            Cost distanceFromLandmarkToTargetNode = nodeCostMap.get(targetNode);

            Cost absCost = Cost.abs(distanceFromLandmarkToCurrentNode.subtract(distanceFromLandmarkToTargetNode));

            if (absCost.greaterThan(maxCost)) {
                maxCost = absCost;
            }
        }

        return maxCost;
    }
}
