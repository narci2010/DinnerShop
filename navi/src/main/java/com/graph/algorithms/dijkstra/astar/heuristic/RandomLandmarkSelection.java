package com.graph.algorithms.dijkstra.astar.heuristic;

import com.graph.algorithms.ShortestPath;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RandomLandmarkSelection {
    private List<Node> roadNetworkNodes = new ArrayList<>();
    private RoadNetwork roadNetwork;


    public RandomLandmarkSelection(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
        for (Node node : roadNetwork) {
            roadNetworkNodes.add(node);
        }

    }

    public Map<Node, Map<Node, Cost>> precomputeDistances(Integer numberOfLandmarks) {
        Map<Node, Map<Node, Cost>> distanceFromLandmarkToNodes = new HashMap<>();

        final int[] indexOfNodesInArray = new Random().ints(0, roadNetworkNodes.size() - 1).distinct().limit(numberOfLandmarks).toArray();


        for (int i : indexOfNodesInArray) {
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(roadNetwork);
            Node landmark = roadNetworkNodes.get(i);
            dijkstraAlgorithm.calculateShortestPathsFromSource(landmark);

            for (Node node : roadNetworkNodes) {
                ShortestPath path = dijkstraAlgorithm.getPath(node.getId());

                if (!distanceFromLandmarkToNodes.containsKey(landmark)) {
                    Map<Node, Cost> costs = new HashMap<>();

                    costs.put(node, path.getCost());
                    distanceFromLandmarkToNodes.put(landmark, costs);
                } else {
                    distanceFromLandmarkToNodes.get(landmark).put(node, path.getCost());

                }

            }


        }
        return distanceFromLandmarkToNodes;
    }

}
