package com.graph.algorithms;

import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;

import java.util.*;

public class DijkstraAlgorithm {
    private RoadNetwork roadNetwork;
    private Map<Node, Cost> distance;
    private Queue<Arc> unsettledNodes;
    private Map<Node, Node> predecessors;
    private Set<Node> settledNodes;

    public DijkstraAlgorithm(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
    }

    public void calculateShortestPathsFromSource(Node sourceNode) {
        calculateShortestPath(sourceNode, null);
    }

    public ShortestPath calculateShortestPath(Node startNode, Node endNode) {

        initializeInternalData();
        setMaxDistanceToAllNodes();

        //set startNode distance cost to zero
        distance.replace(startNode, new Cost(0));

        //add first node as unsettled
        unsettledNodes.add(new Arc(startNode, new Cost(0)));

        while (!unsettledNodes.isEmpty() && !settledNodes.contains(endNode)) {
            Node nodeWithLowestDistanceFromSourceNode = unsettledNodes.poll().getHeadNode();
            settledNodes.add(nodeWithLowestDistanceFromSourceNode);
            calculateDistancesAndAddUnsettledNodes(nodeWithLowestDistanceFromSourceNode);

        }
        return getPath(endNode);
    }

    private void setMaxDistanceToAllNodes() {
        roadNetwork.getAdjacentArcs().keySet().forEach(node -> distance.put(node, new Cost(Integer.MAX_VALUE)));
    }

    private void initializeInternalData() {
        settledNodes = new HashSet<>();
        unsettledNodes = new PriorityQueue<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
    }

    private void calculateDistancesAndAddUnsettledNodes(Node currentNode) {

        List<Arc> arcs = roadNetwork.getAdjacentArcs().get(currentNode);

        for (Arc arc : arcs) {
            Node headNode = arc.getHeadNode();
            Cost costToNode = distance.get(headNode);
            Cost actualCost = distance.get(currentNode).addCost(arc.getCost());

            if (costToNode.gratherThan(actualCost)) {
                //found shorter path

                distance.replace(headNode, distance.get(currentNode).addCost(arc.getCost()));

                predecessors.put(arc.getHeadNode(), currentNode);

                unsettledNodes.remove(arc);
                unsettledNodes.offer(arc);

            }
        }
    }


    public ShortestPath getPath(Node target) {
        Deque<Node> path = new LinkedList<>();

        Cost totalCost = new Cost(0);

        Node step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.push(step);
        totalCost = totalCost.addCost(distance.get(target));

        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.push(step);
        }


        return new ShortestPath(path,totalCost);
    }

}
