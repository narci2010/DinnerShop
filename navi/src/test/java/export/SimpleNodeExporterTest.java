package export;

import com.export.impl.SimpleNodeExporter;
import com.graph.model.Arc;
import com.graph.model.Coordinate;
import com.graph.model.Cost;
import com.graph.model.Node;
import org.junit.Assert;
import org.junit.Test;

public class SimpleNodeExporterTest {
    @Test
    public void shouldExportNodeAsCoordinateString() {
        Coordinate coordinate = new Coordinate(10.11, 12.22);
        Node node = new Node(1, coordinate);
        Arc arc1 = new Arc(new Node(2, 2.0, 3.0), new Cost(2));
        Arc arc2 = new Arc(new Node(3, 3.0, 4.0), new Cost(3));


        node.addOutgoingArcs(arc1);
        node.addOutgoingArcs(arc2);

        String export = node.export(new SimpleNodeExporter());
        Assert.assertEquals("10.11,12.22", export);
    }
}
