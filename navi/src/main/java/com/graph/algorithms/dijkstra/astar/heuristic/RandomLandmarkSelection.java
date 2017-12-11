package com.graph.algorithms.dijkstra.astar.heuristic;

import com.graph.algorithms.ShortestPath;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLandmarkSelection {
    private List<Node> roadNetworkNodes = new ArrayList<>();
    private RoadNetwork roadNetwork;


    public RandomLandmarkSelection(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
        for (Node node : roadNetwork) {
            roadNetworkNodes.add(node);
        }

    }

    public List<List<Cost>> precomputeDistances(Integer numberOfLandmarks) {

        final int[] indexOfNodesInArray = new Random().ints(0, roadNetworkNodes.size() - 1).distinct().limit(numberOfLandmarks).toArray();

        List<List<Cost>> distancesFromLandToNodes = new ArrayList<>();

        for (int i : indexOfNodesInArray) {
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(roadNetwork);
            dijkstraAlgorithm.calculateShortestPathsFromSource(i);

            List<Cost> costs = new ArrayList<>(roadNetworkNodes.size());

            for (Node node : roadNetworkNodes) {
                ShortestPath path = dijkstraAlgorithm.getPath(node.getId());
                costs.add(path.getCost());
            }
            distancesFromLandToNodes.add(costs);


        }
        return distancesFromLandToNodes;
    }

}
