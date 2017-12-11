package export;

import com.export.impl.SimpleCoordinateExporter;
import com.graph.model.Coordinate;
import org.junit.Assert;
import org.junit.Test;

public class CoordinateExporterTest {
    @Test
    public void shouldExportCoordinate(){
        Coordinate coordinate = new Coordinate(10.11,12.22);

        String export = coordinate.export(new SimpleCoordinateExporter());
        Assert.assertEquals("10.11,12.22", export);
    }

}
