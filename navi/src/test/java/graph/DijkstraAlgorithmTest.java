package graph;

import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DijkstraAlgorithmTest {


    @Before
    public void init(){


    }

    @Test
    public void shouldReturnShortestPath(){

        Node one = new Node(1, 2.0, 2.0);
        Node two = new Node(2, 3.0, 3.0);
        Node three = new Node(3, 4.0, 4.0);
        Node four = new Node(4, 4.0, 4.0);
        Node five = new Node(5, 4.0, 4.0);
        Node six = new Node(6, 4.0, 4.0);
        Node seven = new Node(7, 4.0, 4.0);
        Node eight = new Node(8, 4.0, 4.0);
        Node nine = new Node(9, 4.0, 4.0);


        RoadNetwork graph = new RoadNetwork();
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
        dijkstraAlgorithm.calculateShortestPathsFromSource(one);

        Assert.assertEquals("ShortestPath{cost=Cost{seconds=5}, nodes=[Node{id=1}, Node{id=5}, Node{id=6}, Node{id=7}, Node{id=8}, Node{id=4}]}",
                dijkstraAlgorithm.getPath(four).toString());

    }

    @Test
    public void emptyPath(){
        Node one = new Node(1, 2.0, 2.0);
        Node two = new Node(2, 3.0, 3.0);
        Node three = new Node(3, 4.0, 4.0);
        Node four = new Node(4, 4.0, 4.0);
        Node five = new Node(5, 4.0, 4.0);
        Node six = new Node(6, 4.0, 4.0);
        Node seven = new Node(7, 4.0, 4.0);
        Node eight = new Node(8, 4.0, 4.0);
        Node nine = new Node(9, 4.0, 4.0);

        Node ten = new Node(10, 4.0, 4.0);
        Node eleven = new Node(11, 4.0, 4.0);


        RoadNetwork graph = new RoadNetwork();
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

        graph.addEdge(ten, eleven, new Cost(1));


        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
        dijkstraAlgorithm.calculateShortestPath(ten, eleven);
        Assert.assertEquals("ShortestPath{cost=Cost{seconds=0}, nodes=[]}",
                dijkstraAlgorithm.getPath(one).toString());
    }



}
