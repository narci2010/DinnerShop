package export;

import com.export.impl.SimplePathExporter;
import com.graph.algorithms.ShortestPath;
import com.graph.model.Coordinate;
import com.graph.model.Cost;
import com.graph.model.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplePathExporterTest {
    @Test
     public void shouldReturnPathAsCoordinateString(){
        Deque<Node> nodes = new ArrayDeque<>();
        nodes.add(new Node(0, new Coordinate(1.11,2.22)));
        nodes.add(new Node(1, new Coordinate(3.33,4.44)));


        ShortestPath shortestPath = new ShortestPath(nodes,new Cost(0));
        String export = shortestPath.export(new SimplePathExporter());

        Assert.assertEquals("1.11,2.22,3.33,4.44",export);


    }
}
