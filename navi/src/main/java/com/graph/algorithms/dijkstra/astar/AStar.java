package com.graph.algorithms.dijkstra.astar;

import com.graph.algorithms.dijkstra.AbstractDijkstraAlgorithm;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.algorithms.dijkstra.astar.heuristic.RandomLandmarkSelection;
import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.graph.model.SPEntry;
import com.navigation.RoadNetwork;

import java.util.*;


//Not ready
/*
public class AStar extends AbstractDijkstraAlgorithm {

    private List<List<Cost>> distanceFromLandmarkToNodes;
    private RandomLandmarkSelection landmarkSelection;
    private SPEntry[] heuristic;

    public AStar(RoadNetwork roadNetwork, RandomLandmarkSelection landmarkSelection) {
        super(roadNetwork);
        this.landmarkSelection = landmarkSelection;
        heuristic = new SPEntry[roadNetworkNodes.size()];
    }

    public void precomputeDistances(Integer numberOfLandmarks) {
        distanceFromLandmarkToNodes = landmarkSelection.precomputeDistances(numberOfLandmarks);
    }

    @Override
    protected void exploreNeighbours(int currentNodeId) {
        List<Arc> arcs = roadNetworkNodes.get(currentNodeId).getOutgoingArcs();

        for (Arc arc : arcs) {
            int headNodeId = arc.getHeadNode().getId();

            Cost costToNode = distances[headNodeId].getCost();
            Cost actualCost = distances[currentNodeId].getCost().addCost(arc.getCost());
            Cost costWithHeuristic = actualCost.addCost((calculateHeuristic(headNodeId)));
            SPEntry heuristicEntry = new SPEntry(headNodeId, costWithHeuristic, null);


            if (costToNode.greaterThan(costWithHeuristic)) {
                //found shorter path

                SPEntry spEntry = distances[headNodeId];
                spEntry.setCost(actualCost);
                //Not sure is it true
                spEntry.setParent(distances[currentNodeId]);


                unsettledNodes.remove(heuristicEntry);
                unsettledNodes.offer(heuristicEntry);

            }
        }

    }

    private Cost calculateHeuristic(int currentNodeId) {
        Cost maxCost = new Cost(0);

        for (List<Cost> costs : distanceFromLandmarkToNodes) {
            Cost distanceFromLandmarkToCurrentNode = costs.get(currentNodeId);
            Cost distanceFromLandmarkToTargetNode = costs.get(targetNode.getNodeId());
            Cost absCost = Cost.abs(distanceFromLandmarkToCurrentNode.subtract(distanceFromLandmarkToTargetNode));

            if (absCost.greaterThan(maxCost)) {
                maxCost = absCost;
            }
        }

        return maxCost;
    }
}
*/
