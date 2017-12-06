package com.graph.algorithms.dijkstra.astar;

import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.algorithms.dijkstra.astar.heuristic.RandomLandmarkSelection;
import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.graph.model.SPEntry;
import com.navigation.RoadNetwork;

import java.util.*;


//Not ready
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
    protected void addToUnsettledNodes(SPEntry spEntry) {
        SPEntry aStarEntry = new SPEntry(spEntry.getNodeId(), spEntry.getCost().addCost(calculateHeuristic(roadNetworkNodes.get(spEntry.getNodeId())))
                , spEntry.getParent());

        unsettledNodes.remove(aStarEntry);
        unsettledNodes.offer(aStarEntry);


    }

    private Cost calculateHeuristic(Node currentNode) {
        Cost maxCost = new Cost(0);
        for (Node landmark : distanceFromLandmarkToNodes.keySet()) {
            Map<Node, Cost> nodeCostMap = distanceFromLandmarkToNodes.get(landmark);
            Cost distanceFromLandmarkToCurrentNode = nodeCostMap.get(currentNode);
            Cost distanceFromLandmarkToTargetNode = nodeCostMap.get(roadNetworkNodes.get(targetNode.getNodeId()));

            Cost absCost = Cost.abs(distanceFromLandmarkToCurrentNode.subtract(distanceFromLandmarkToTargetNode));

            if (absCost.greaterThan(maxCost)) {
                maxCost = absCost;
            }
        }

        return maxCost;
    }
}
