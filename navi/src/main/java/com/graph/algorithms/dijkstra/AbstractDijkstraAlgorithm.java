package com.graph.algorithms.dijkstra;

import com.graph.algorithms.DijkstraAlgorithm;
import com.graph.algorithms.ShortestPath;
import com.graph.weighting.Weighting;
import com.graph.model.*;

import java.util.*;

public abstract class AbstractDijkstraAlgorithm implements DijkstraAlgorithm {
    protected List<Node> roadNetworkNodes = new ArrayList<>();
    protected SPEntry[] distances;
    protected Queue<SPEntry> unsettledNodes;
    protected Set<SPEntry> settledNodes;
    protected SPEntry targetNode;

    public AbstractDijkstraAlgorithm(Graph<Node> roadNetwork) {
        List<SPEntry> spEntries = new ArrayList<>(30000);
        for (Node node : roadNetwork) {
            roadNetworkNodes.add(node);
            SPEntry spEntry = createSPEntry(node);
            spEntries.add(spEntry);
        }

        distances = spEntries.toArray(new SPEntry[spEntries.size()]);


    }

    @Override
    public ShortestPath calculateShortestPath(Node sourceNode, Node targetNode, Weighting weighting) {
        return calculateShortestPath(sourceNode.getId(), targetNode.getId(), weighting);
    }

    @Override
    public void calculateShortestPathsFromSource(Node sourceNode, Weighting weighting) {
        calculateShortestPath(sourceNode.getId(), null, weighting);
    }

    public void calculateShortestPathsFromSource(int sourceNodeId, Weighting weighting) {
        calculateShortestPath(sourceNodeId, null, weighting);
    }


    public ShortestPath calculateShortestPath(Integer startNodeId, Integer endNodeId, Weighting weighting) {
        Optional.ofNullable(endNodeId).ifPresent(nodeId -> {
            targetNode = new SPEntry();
            targetNode.setNodeId(nodeId);
        });


        initializeInternalData();
        initializeFirstEntry(distances[startNodeId]);


        while (!unsettledNodes.isEmpty() || settledNodes.contains(targetNode)) {

            SPEntry spEntryWithLowestDistanceFromSource = unsettledNodes.poll();
            settledNodes.add(distances[spEntryWithLowestDistanceFromSource.getNodeId()]);

            int currentNodeId = spEntryWithLowestDistanceFromSource.getNodeId();

            exploreNeighbours(currentNodeId, weighting);

        }
        return readPathTo(endNodeId);
    }

    protected abstract void exploreNeighbours(int currentNodeId, Weighting weighting);


    @Override
    public ShortestPath readPathTo(Node targetNode) {
        return readPathTo(targetNode.getId());

    }

    public ShortestPath readPathTo(Integer targetNodeId) {
        if (Optional.ofNullable(targetNodeId).isPresent()) {
            Deque<Node> path = new LinkedList<>();

            SPEntry spEntry = distances[targetNodeId];
            if (spEntry.getParent() != null) {
                path.push(roadNetworkNodes.get(targetNodeId));

                Cost totalCost = spEntry.getCost();
                while (spEntry.getParent() != null) {
                    path.push(roadNetworkNodes.get(spEntry.getParent().getNodeId()));
                    spEntry = spEntry.getParent();
                }

                return new ShortestPath(path, totalCost);
            }
        }
        return new ShortestPath(new ArrayDeque<>(), new Cost(0));

    }


    private SPEntry createSPEntry(Node node) {
        SPEntry spEntry = new SPEntry();
        spEntry.setCost(new Cost(Integer.MAX_VALUE));
        spEntry.setNodeId(node.getId());
        spEntry.setParent(null);
        return spEntry;
    }


    protected void initializeFirstEntry(SPEntry distance) {
        //set startNode distance cost to zero
        distance.setCost(new Cost(0));

        //export first node as unsettled
        unsettledNodes.add(distance);
    }

    protected void initializeInternalData() {
        settledNodes = new HashSet<>();
        unsettledNodes = new PriorityQueue<>();
        for (SPEntry distance : distances) {
            distance.setParent(null);
            distance.setCost(new Cost(Integer.MAX_VALUE));
        }

    }


}
