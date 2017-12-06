package com.graph.algorithms.dijkstra;

import com.graph.algorithms.ShortestPath;
import com.graph.model.*;

import java.util.*;

public class DijkstraAlgorithm {
    private List<Node> roadNetworkNodes = new ArrayList<>();
    private SPEntry[] distances;
    private Queue<SPEntry> unsettledNodes;
    private Set<SPEntry> settledNodes;
    protected SPEntry targetNode;

    public DijkstraAlgorithm(Graph<Node> roadNetwork) {
        List<SPEntry> spEntries = new ArrayList<>(30000);

        for (Node node : roadNetwork) {
            roadNetworkNodes.add(node);
            SPEntry spEntry = createSPEntry(node);
            spEntries.add(spEntry);
        }

        distances = spEntries.toArray(new SPEntry[spEntries.size()]);


    }

    public void calculateShortestPathsFromSource(Node sourceNode) {
        calculateShortestPath(sourceNode.getId(), null);
    }

    public ShortestPath calculateShortestPath(Node startNode, Node endNode) {

        return calculateShortestPath(startNode.getId(), endNode.getId());
    }

    public ShortestPath calculateShortestPath(Integer startNodeId, Integer endNodeId) {
        Optional.ofNullable(endNodeId).ifPresent(nodeId -> {
            targetNode = new SPEntry();
            targetNode.setNodeId(nodeId);
        });


        initializeInternalData();

        //set startNode distance cost to zero
        SPEntry spEntry = distances[startNodeId];
        spEntry.setCost(new Cost(0));

        //add first node as unsettled
        unsettledNodes.add(distances[startNodeId]);

        while (!unsettledNodes.isEmpty() && !settledNodes.contains(targetNode)) {
            SPEntry spEntryWithLowestDistanceFromSource = unsettledNodes.poll();
            settledNodes.add(spEntryWithLowestDistanceFromSource);
            calculateDistancesAndAddUnsettledNodes(spEntryWithLowestDistanceFromSource);

        }
        return getPath(endNodeId);
    }

    public ShortestPath getPath(Node targetNode) {
        return getPath(targetNode.getId());

    }

    public ShortestPath getPath(Integer targetNodeId){
        if (Optional.ofNullable(targetNodeId).isPresent()) {
            Deque<Node> path = new LinkedList<>();

            SPEntry spEntry = distances[targetNodeId];
            Cost totalCost = spEntry.getCost();
            while (spEntry.getParent()!=null) {
                path.push(roadNetworkNodes.get(spEntry.getParent().getNodeId()));
                spEntry = spEntry.getParent();
            }

            return new ShortestPath(path,totalCost);
        }
        return new ShortestPath(new ArrayDeque<>(),new Cost(0));
    }


    private SPEntry createSPEntry(Node node) {
        SPEntry spEntry = new SPEntry();
        spEntry.setCost(new Cost(Integer.MAX_VALUE));
        spEntry.setNodeId(node.getId());
        spEntry.setParent(null);
        return spEntry;
    }

    private void initializeInternalData() {
        settledNodes = new HashSet<>();
        unsettledNodes = new PriorityQueue<>();
        for (SPEntry distance : distances) {
            distance.setParent(null);
            distance.setCost(new Cost(Integer.MAX_VALUE));
        }

    }

    private void calculateDistancesAndAddUnsettledNodes(SPEntry currentSPEntry) {
        List<Arc> arcs = roadNetworkNodes.get(currentSPEntry.getNodeId()).getOutgoingArcs();

        for (Arc arc : arcs) {
            Node headNode = arc.getHeadNode();
            Cost costToNode = distances[headNode.getId()].getCost();
            Cost actualCost = getActualCost(currentSPEntry, arc);

            if (costToNode.greaterThan(actualCost)) {
                //found shorter path

                SPEntry spEntry = distances[headNode.getId()];
                spEntry.setCost(actualCost);
                spEntry.setParent(currentSPEntry);

                addToUnsettledNodes(spEntry);

            }
        }
    }

    protected void addToUnsettledNodes(SPEntry spEntry) {
        unsettledNodes.remove(spEntry);
        unsettledNodes.offer(spEntry);
    }

    private Cost getActualCost(SPEntry currentSPEntry, Arc arc) {
        return distances[currentSPEntry.getNodeId()].getCost().addCost(arc.getCost());
    }

}
