package com.graph.algorithms.dijkstra;

import com.graph.model.*;

import java.util.List;

public class DijkstraAlgorithm extends AbstractDijkstraAlgorithm {
    public DijkstraAlgorithm(Graph<Node> roadNetwork) {
        super(roadNetwork);
    }

    @Override
    protected void exploreNeighbours(int currentNodeId) {
        List<Arc> arcs = roadNetworkNodes.get(currentNodeId).getOutgoingArcs();

        for (Arc arc : arcs) {
            int headNodeId = arc.getHeadNode().getId();

            Cost costToNode = distances[headNodeId].getCost();
            Cost actualCost = distances[currentNodeId].getCost().addCost(arc.getCost());


            if (costToNode.greaterThan(actualCost)) {
                //found shorter path

                SPEntry spEntry = distances[headNodeId];
                spEntry.setCost(actualCost);
                //Not sure is it true
                spEntry.setParent(distances[currentNodeId]);

                unsettledNodes.remove(spEntry);
                unsettledNodes.offer(spEntry);

            }
        }
    }
}

