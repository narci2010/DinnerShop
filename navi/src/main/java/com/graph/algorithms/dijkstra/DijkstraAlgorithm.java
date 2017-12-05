package com.graph.algorithms.dijkstra;

import com.graph.algorithms.ShortestPath;
import com.graph.model.Arc;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.graph.model.SPEntry;
import com.navigation.RoadNetwork;

import java.util.*;

public class DijkstraAlgorithm {
    private RoadNetwork roadNetwork;
    private SPEntry[] distances;
    private Queue<SPEntry> unsettledNodes;
    private Map<Node, Node> predecessors;
    private Set<SPEntry> settledNodes;
    protected SPEntry targetNode;

    public DijkstraAlgorithm(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
    }

    public void calculateShortestPathsFromSource(Node sourceNode) {
        calculateShortestPath(sourceNode, null);
    }

    public ShortestPath calculateShortestPath(Node startNode, Node endNode) {
        targetNode = new SPEntry();
        targetNode.setNodeId(endNode.getId());

        initializeInternalData();
        setMaxDistanceToAllNodes();

        //set startNode distance cost to zero
        SPEntry spEntry = distances[startNode.getId()];
        spEntry.setCost(new Cost(0));

        //add first node as unsettled
        unsettledNodes.add(distances[startNode.getId()]);

        while (!unsettledNodes.isEmpty() && !settledNodes.contains(targetNode)) {
            SPEntry spEntryWithLowestDistanceFromSource = unsettledNodes.poll();
            settledNodes.add(spEntryWithLowestDistanceFromSource);
            calculateDistancesAndAddUnsettledNodes(spEntryWithLowestDistanceFromSource);

        }
        return getPath(endNode);
    }


    public ShortestPath getPath(Node target) {
        Deque<Node> path = new LinkedList<>();

        Cost totalCost = new Cost(0);

        Node step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return new ShortestPath(new LinkedList<>(), new Cost(0));
        }
        path.push(step);
        totalCost = totalCost.addCost(distance.get(target));

        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.push(step);
        }


        return new ShortestPath(path, totalCost);
    }

    private void setMaxDistanceToAllNodes() {

        List<SPEntry> spEntries = new ArrayList<>(30000);
        roadNetwork.forEach(node -> {
            SPEntry spEntry = new SPEntry();
            spEntry.setCost(new Cost(Integer.MAX_VALUE));
            spEntry.setNodeId(node.getId());
            spEntry.setParent(null);
            spEntries.add(spEntry);
        });

        distances = spEntries.toArray(new SPEntry[spEntries.size()]);


        roadNetwork.forEach(node -> distance.put(node, new Cost(Integer.MAX_VALUE)));

//        roadNetwork.getAdjacentArcs().keySet().forEach(node -> distance.put(node, new Cost(Integer.MAX_VALUE)));
    }

    private void initializeInternalData() {
        settledNodes = new HashSet<>();
        unsettledNodes = new PriorityQueue<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
    }

    private void calculateDistancesAndAddUnsettledNodes(Node currentNode) {


 /*       List<Arc> arcs = roadNetwork.getAdjacentArcs().get(currentNode);

        for (Arc arc : arcs) {
            Node headNode = arc.getHeadNode();
            Cost costToNode = distance.get(headNode);
            Cost actualCost = getActualCost(currentNode, arc);

            if (costToNode.greaterThan(actualCost)) {
                //found shorter path

                distance.replace(headNode, actualCost);

                predecessors.put(arc.getHeadNode(), currentNode);

                addToUnsettledNodes(arc);

            }
        }*/
    }

    protected void addToUnsettledNodes(Arc arc) {
        unsettledNodes.remove(arc);
        unsettledNodes.offer(arc);
    }

    private Cost getActualCost(Node currentNode, Arc arc) {
        return distance.get(currentNode).addCost(arc.getCost());
    }

}
