package graph;

import com.graph.algorithms.ShortestPath;
import com.graph.algorithms.dijkstra.astar.AStar;
import com.graph.algorithms.dijkstra.astar.heuristic.RandomLandmarkSelection;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;
import org.junit.Assert;
import org.junit.Test;

public class AStarTest {
    @Test
    public void AStarShortestPath() {
        RoadNetwork graph = new RoadNetwork();
        Node one = new Node(0, 2.0, 2.0);
        graph.addNode(one);
        Node two = new Node(1, 3.0, 3.0);
        graph.addNode(two);
        Node three = new Node(2, 4.0, 4.0);
        graph.addNode(three);
        Node four = new Node(3, 4.0, 4.0);
        graph.addNode(four);
        Node five = new Node(4, 4.0, 4.0);
        graph.addNode(five);
        Node six = new Node(5, 4.0, 4.0);
        graph.addNode(six);
        Node seven = new Node(6, 4.0, 4.0);
        graph.addNode(seven);


        graph.addEdge(one, two, new Cost(2));
        graph.addEdge(two, three, new Cost(2));
        graph.addEdge(three, four, new Cost(2));
        graph.addEdge(four, five, new Cost(2));

        graph.addEdge(one, six, new Cost(1));
        graph.addEdge(six, seven, new Cost(1));
        graph.addEdge(seven, five, new Cost(7));


        RandomLandmarkSelection randomLandmarkSelection = new RandomLandmarkSelection(graph);
        AStar aStar = new AStar(graph, randomLandmarkSelection);

        aStar.precomputeDistances(2);
        ShortestPath shortestPath = aStar.calculateShortestPath(one, five);

        Assert.assertEquals("ShortestPath{cost=Cost{seconds=8}, nodes=[Node{id=1}, Node{id=2}, Node{id=3}, Node{id=4}, Node{id=5}]}", shortestPath.toString());

        System.out.println(shortestPath);
    }

}
