package graph;

import com.graph.algorithms.DijkstraAlgorithm;
import com.graph.model.Cost;
import com.graph.model.Node;
import com.navigation.RoadNetwork;
import org.junit.Test;

public class DijkstraAlgorithmTest {


    @Test
    public void test(){
        Node one = new Node(1, 2.0, 2.0);
        Node two = new Node(2, 3.0, 3.0);

        Node three = new Node(3, 4.0, 4.0);
        Node four = new Node(4, 4.0, 4.0);

        Node five = new Node(5, 4.0, 4.0);


        RoadNetwork graph = new RoadNetwork();
        graph.addEdge(one, two, new Cost(1));
        graph.addEdge(two, four, new Cost(2));
        graph.addEdge(one, three, new Cost(3));
        graph.addEdge(three, four, new Cost(3));
        graph.addEdge(one, five, new Cost(1));

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);

        dijkstraAlgorithm.calculateShorthestPath(one, null);

        System.out.println(dijkstraAlgorithm.getPath(four));





    }

}
