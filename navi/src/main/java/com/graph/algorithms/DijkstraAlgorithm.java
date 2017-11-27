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
    private Set<Node> settledNodes;
    private Map<Node, Node> predecessors;

    public DijkstraAlgorithm(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
    }

    public ShortestPath calculateShorthestPath(Node startNode, Node endNode) {
        ShortestPath shortestPath = new ShortestPath();

        settledNodes = new HashSet<>();
        unsettledNodes = new PriorityQueue<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();

        roadNetwork.getAdjacentArcs().keySet().forEach(node -> distance.put(node, new Cost(Integer.MAX_VALUE)));
        distance.replace(startNode, new Cost(0));


        unsettledNodes.add(new Arc(startNode,new Cost(0)));


        while (!unsettledNodes.isEmpty() && !settledNodes.contains(endNode)) {
            Arc poll = unsettledNodes.poll();
            settledNodes.add(poll.getHeadNode());
            findNextNode(poll);

        }


        return shortestPath;
    }

    private void findNextNode(Arc nodeWithCost) {

        List<Arc> arcs = roadNetwork.getAdjacentArcs().get(nodeWithCost.getHeadNode());

        for (Arc arc : arcs) {
            Node headNode = arc.getHeadNode();
            Cost costToNode = distance.get(headNode);
            if (costToNode.compareTo(distance.get(nodeWithCost.getHeadNode()).addCost(arc.getCost())) > 0) {
                distance.replace(headNode, distance.get(nodeWithCost.getHeadNode()).addCost(arc.getCost()));

                predecessors.put(arc.getHeadNode(),nodeWithCost.getHeadNode());

                unsettledNodes.remove(arc);
                unsettledNodes.offer(arc);

            }
        }
    }



    public LinkedList<Node> getPath(Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
