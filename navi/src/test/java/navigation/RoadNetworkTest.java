package navigation;

import com.graph.model.Cost;
import com.graph.model.Graph;
import com.graph.model.Node;
import com.navigation.RoadNetwork;
import org.junit.Assert;
import org.junit.Test;

public class RoadNetworkTest {


    @Test
    public void shouldConstructEmptyGraph() {
        Graph graph = new RoadNetwork();
        Assert.assertEquals(graph.toString(), "");

    }


    @Test
    public void shouldAddThirdArc() {
        Node tailNode = new Node(0, 2.0, 2.0);
        Node headNode = new Node(1, 3.0, 3.0);

        Node thirdNode = new Node(2, 4.0, 4.0);

        Graph graph = new RoadNetwork();

        graph.addNode(tailNode);
        graph.addNode(headNode);
        graph.addNode(thirdNode);

        graph.addEdge(tailNode, headNode, new Cost(2));
        graph.addEdge(tailNode, thirdNode, new Cost(2));

        Assert.assertEquals("Node{id=0} : [Arc{headNodeId=1,Cost{seconds=2}}, Arc{headNodeId=2,Cost{seconds=2}}]\n" +
                "Node{id=1} : [Arc{headNodeId=0,Cost{seconds=2}}]\n" +
                "Node{id=2} : [Arc{headNodeId=0,Cost{seconds=2}}]\n", graph.toString());
    }
}


