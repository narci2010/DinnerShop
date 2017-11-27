package graph;

import com.graph.algorithms.DijkstraAlgorithm;
import com.graph.model.Cost;
import com.graph.model.Graph;
import com.graph.model.Node;
import com.navigation.RoadNetwork;
import org.junit.Before;
import org.junit.Test;

public class DijkstraAlgorithmTest {


    @Before
    public void init(){


    }

    @Test
    public void test(){

        Node one = new Node(1, 2.0, 2.0);
        Node two = new Node(2, 3.0, 3.0);

        Node three = new Node(3, 4.0, 4.0);
        Node four = new Node(4, 4.0, 4.0);

        Node five = new Node(5, 4.0, 4.0);

        Node six = new Node(6, 4.0, 4.0);
        Node seven = new Node(7, 4.0, 4.0);
        Node eight = new Node(8, 4.0, 4.0);


        RoadNetwork graph = new RoadNetwork();
        graph.addEdge(one, two, new Cost(3));
        graph.addEdge(two, four, new Cost(3));
        graph.addEdge(one, three, new Cost(4));
        graph.addEdge(three, four, new Cost(3));
        graph.addEdge(one, five, new Cost(1));
        graph.addEdge(five, six, new Cost(1));
        graph.addEdge(six, seven, new Cost(1));
        graph.addEdge(seven, eight, new Cost(1));
        graph.addEdge(eight, four, new Cost(1));




        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);

        dijkstraAlgorithm.calculateShorthestPath(one, six);

        System.out.println(dijkstraAlgorithm.getPath(six));





    }

}
