package com.graph.algorithms;

import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;

import java.util.*;

public class DijkstraAlgorithm {
    private RoadNetwork roadNetwork;
    private Map<Node, Cost> distance;
    Queue<NodeWithCost> unsettledNodes;
    Set<Node> settledNodes;
    private Map<Node, Node> predecessors;

    Cost totalCost = new Cost(0);
    List<Node> path = new ArrayList<>();

    class NodeWithCost implements Comparable<NodeWithCost> {
        Node node;
        Cost cost;

        public NodeWithCost(Node node, Cost cost) {
            this.node = node;
            this.cost = cost;
        }

        public Node getNode() {
            return node;
        }

        public Cost getCost() {
            return cost;
        }

        @Override
        public int compareTo(NodeWithCost o) {
            return cost.compareTo(o.cost);
        }
    }

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


        unsettledNodes.add(new NodeWithCost(startNode, new Cost(0)));

        path.add(startNode);

        while (!unsettledNodes.isEmpty() || settledNodes.contains(endNode)) {
            NodeWithCost poll = unsettledNodes.poll();
            settledNodes.add(poll.getNode());
            findNextNode(poll);

        }


        return shortestPath;
    }

    private void findNextNode(NodeWithCost nodeWithCost) {

        List<Arc> arcs = roadNetwork.getAdjacentArcs().get(nodeWithCost.node);

        for (Arc arc : arcs) {
            Node headNode = arc.getHeadNode();
            Cost costToNode = distance.get(headNode);
            if (costToNode.compareTo(distance.get(nodeWithCost.node).addCost(arc.getCost())) > 0) {
                distance.replace(headNode, distance.get(nodeWithCost.node).addCost(arc.getCost()));

                predecessors.put(arc.getHeadNode(),nodeWithCost.node);

                NodeWithCost temp = new NodeWithCost(headNode, distance.get(headNode));
                unsettledNodes.remove(temp);
                unsettledNodes.offer(temp);

            }
        }

/*
        Optional<Arc> min = arcs.stream()
                .filter(arc -> !settledNodes.contains(arc.getHeadNode()))
                .min(Comparator.comparing(Arc::getCost));


        if (min.isPresent()) {
            Arc arcWithMinimalCost = min.get();
            distance.put(arcWithMinimalCost.getHeadNode(), distance.get(node).addCost(arcWithMinimalCost.getCost()));
            totalCost = distance.get(node).addCost(arcWithMinimalCost.getCost());
            unsettledNodes.offer(arcWithMinimalCost.getHeadNode());
            path.add(arcWithMinimalCost.getHeadNode());
        }*/


    }

    @Override
    public String toString() {
        return "DijkstraAlgorithm{" +
                "totalCost=" + totalCost +
                ", path=" + path +
                '}';
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
