package graph;

import com.export.impl.DebugPathExporter;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.model.Cost;
import com.graph.model.Graph;
import com.graph.model.Node;
import com.graph.weighting.SimpleWeighing;
import com.navigation.RoadNetwork;
import org.junit.Assert;
import org.junit.Test;

public class DijkstraAlgorithmTest {

    @Test
    public void shouldReturnShortestPath(){
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
        Node eight = new Node(7, 4.0, 4.0);
        graph.addNode(eight);
        Node nine = new Node(8, 4.0, 4.0);
        graph.addNode(nine);



        graph.addEdge(one, two, new Cost(3));
        graph.addEdge(two, four, new Cost(3));
        graph.addEdge(three, nine, new Cost(3));

        graph.addEdge(one, three, new Cost(4));
        graph.addEdge(three, four, new Cost(3));
        graph.addEdge(one, five, new Cost(1));
        graph.addEdge(five, six, new Cost(1));
        graph.addEdge(six, seven, new Cost(1));
        graph.addEdge(seven, eight, new Cost(1));
        graph.addEdge(eight, four, new Cost(1));

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.calculateShortestPathsFromSource(one, new SimpleWeighing());

        Assert.assertEquals("ShortestPath{cost=Cost{seconds=5}, nodes=[Node{id=0}, Node{id=4}, Node{id=5}, Node{id=6}, Node{id=7}, Node{id=3}]}",
                dijkstraAlgorithm.readPathTo(four).export(new DebugPathExporter()));
    }

    @Test
    public void emptyPath(){
        Graph<Node> graph = new RoadNetwork();
        Node zero = new Node(0, 2.0, 2.0);
        graph.addNode(zero);
        Node one = new Node(1, 3.0, 3.0);
        graph.addNode(one);
        Node two = new Node(2, 4.0, 4.0);
        graph.addNode(two);
        Node three = new Node(3, 4.0, 4.0);
        graph.addNode(three);
        Node four = new Node(4, 4.0, 4.0);
        graph.addNode(four);
        Node five = new Node(5, 4.0, 4.0);
        graph.addNode(five);
        Node six = new Node(6, 4.0, 4.0);
        graph.addNode(six);
        Node seven = new Node(7, 4.0, 4.0);
        graph.addNode(seven);
        Node eight = new Node(8, 4.0, 4.0);
        graph.addNode(eight);
        Node nine = new Node(9, 4.0, 4.0);
        graph.addNode(nine);
        Node ten = new Node(10, 4.0, 4.0);
        graph.addNode(ten);
        Node eleven = new Node(11, 4.0, 4.0);
        graph.addNode(eleven);


        graph.addEdge(zero, one, new Cost(3));
        graph.addEdge(one, three, new Cost(3));
        graph.addEdge(two, nine, new Cost(3));

        graph.addEdge(zero, two, new Cost(4));
        graph.addEdge(two, three, new Cost(3));
        graph.addEdge(zero, four, new Cost(1));
        graph.addEdge(four, five, new Cost(1));
        graph.addEdge(five, six, new Cost(1));
        graph.addEdge(six, seven, new Cost(1));
        graph.addEdge(seven, three, new Cost(1));

        graph.addEdge(ten, eleven, new Cost(1));


        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.calculateShortestPath(ten, eleven, new SimpleWeighing());
        Assert.assertEquals("ShortestPath{cost=Cost{seconds=0}, nodes=[]}",
                dijkstraAlgorithm.readPathTo(zero).export(new DebugPathExporter()));
    }



}
