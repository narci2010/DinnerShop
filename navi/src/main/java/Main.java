import com.basicosmparser.controller.OSMParser;
import com.basicosmparser.model.Element;
import com.navigation.RoadNetwork;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        OSMParser p = new OSMParser();                        //Initialization of the parser
        File osmFile = new File("C:\\Users\\TomaszZielichowski\\Documents\\Projects\\DinnerShop\\navi\\src\\main\\resources\\saarland_fix.osm");    //Create a file object for your OSM XML file


        try {
            Instant start = Instant.now();

            Map<String, Element> result = p.parse(osmFile);//Parse OSM data, and put result in a Map object


            RoadNetwork roadNetwork = OSMParser.buildRoadNetwork(result);

            Instant end = Instant.now();

            System.out.println("Duration : " + Duration.between(start, end).getSeconds()); // prints PT1M3.553S

        } catch (IOException | SAXException e) {
            e.printStackTrace();                                //Input/output errors management
        }

    }
}
