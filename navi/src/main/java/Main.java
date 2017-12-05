import com.basicosmparser.controller.OSMParser;
import com.basicosmparser.model.Element;
import com.graph.algorithms.ShortestPath;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.algorithms.dijkstra.astar.AStar;
import com.graph.algorithms.dijkstra.astar.heuristic.RandomLandmarkSelection;
import com.graph.model.Graph;
import com.graph.model.Node;
import com.navigation.RoadNetwork;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        OSMParser p = new OSMParser();
        File osmFile = new File("C:\\Users\\Tomek\\IdeaProjects\\DinnerShop\\navi\\src\\main\\resources\\saarland_fix.osm");    //Create a file object for your OSM XML file


        try {

            Instant startRoadNetworkBuild = Instant.now();
            Map<String, Element> result = p.parse(osmFile);//Parse OSM data, and put result in a Map object

            Instant endRoadNetworkBuild = Instant.now();


            RoadNetwork roadNetwork = OSMParser.buildRoadNetwork(result);

            System.out.println("Duration : " + Duration.between(startRoadNetworkBuild, endRoadNetworkBuild).getSeconds());
            System.out.println(roadNetwork);

   /*         Node[] nodes = roadNetwork.getAdjacentArcs().keySet().toArray(new Node[roadNetwork.getAdjacentArcs().keySet().size()]);

            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(roadNetwork);

            Instant start = Instant.now();

            ShortestPath shortestPath1 = dijkstraAlgorithm.calculateShortestPath(nodes[0], nodes[12311]);

            Instant end = Instant.now();
            System.out.println("Duration : " + Duration.between(start, end).getSeconds());
            System.out.println("Dijkstra SP : " + shortestPath1);


            AStar aStar = new AStar(roadNetwork, new RandomLandmarkSelection(roadNetwork));
            aStar.precomputeDistances(2);

            Instant startAStar = Instant.now();
            ShortestPath shortestPath = aStar.calculateShortestPath(nodes[0], nodes[12311]);
            Instant endAStar = Instant.now();

            System.out.println("Duration : " + Duration.between(startAStar, endAStar).getSeconds());
            System.out.println("AStar SP : " + shortestPath);*/




        } catch (IOException | SAXException e) {
            e.printStackTrace();                                //Input/output errors management
        }

    }
}
