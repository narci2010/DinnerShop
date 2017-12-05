package com.graph.algorithms.dijkstra.astar.heuristic;

import com.graph.algorithms.ShortestPath;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;

import java.util.*;

public class RandomLandmarkSelection {
    private RoadNetwork roadNetwork;


    public RandomLandmarkSelection(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
    }

    public Map<Node, Map<Node, Cost>> precomputeDistances(Integer numberOfLandmarks) {
        Map<Node, Map<Node, Cost>> distanceFromLandmarkToNodes = new HashMap<>();
/*
        Map<Node, List<Arc>> adjacentArcs = roadNetwork.getAdjacentArcs();
        //helper List to use index
        List<Node> nodes = new ArrayList<>(adjacentArcs.keySet());

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(roadNetwork);


        final int[] indexOfNodesInArray = new Random().ints(0, nodes.size()-1).distinct().limit(numberOfLandmarks).toArray();

        for (int i : indexOfNodesInArray) {
            Node landmark = nodes.get(i);
            dijkstraAlgorithm.calculateShortestPathsFromSource(landmark);

            for (Node node : nodes) {
                ShortestPath path = dijkstraAlgorithm.getPath(node);

                if (!distanceFromLandmarkToNodes.containsKey(landmark)) {
                    Map<Node, Cost> costs = new HashMap<>();

                    costs.put(node, path.getCost());
                    distanceFromLandmarkToNodes.put(landmark, costs);
                } else {
                    distanceFromLandmarkToNodes.get(landmark).put(node, path.getCost());

                }

            }


        }
*/
        return distanceFromLandmarkToNodes;
    }
}
