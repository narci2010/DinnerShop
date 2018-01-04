import com.basicosmparser.controller.OSMParser;
import com.basicosmparser.model.Element;
import com.export.impl.SimplePathExporter;
import com.graph.algorithms.ShortestPath;
import com.graph.algorithms.dijkstra.DijkstraAlgorithm;
import com.graph.model.Coordinate;
import com.graph.model.Node;
import com.graph.weighting.SimpleWeighing;
import com.navigation.RoadNetwork;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        OSMParser p = new OSMParser();
        File osmFile = new File(Main.class.getClassLoader().getResource("wlkp.osm").getFile());    //Create a file object for your OSM XML file


        try {

            Instant startRoadNetworkBuild = Instant.now();
            Map<String, Element> result = p.parse(osmFile);//Parse OSM data, and put result in a Map object

            Instant endRoadNetworkBuild = Instant.now();


            RoadNetwork roadNetwork = OSMParser.buildRoadNetwork(result);

            System.out.println("Duration : " + Duration.between(startRoadNetworkBuild, endRoadNetworkBuild).getSeconds());

            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(roadNetwork);

            Instant start = Instant.now();

            dijkstraAlgorithm.calculateShortestPathsFromSource(12, new SimpleWeighing());

            Instant end = Instant.now();
            System.out.println("Duration : " + Duration.between(start, end).getSeconds());

            /**
             * @param args
             * @throws IOException
             */
            int port = 8888;
            ServerSocket server = new ServerSocket(port);
            BufferedReader in = null;
            PrintWriter out = null;

            int i = 0;
            while (true)

            {
                System.out.println("\u001b[1m\u001b[34m[" + (++i) + "] "
                        + "Waiting for query on port " + port + " ...\u001b[0m");
                Socket client = server.accept();
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                // Get the request string.
                String request = in.readLine();
                System.out.println("Request string is \""
                        + (request.length() < 99 ? request : request.substring(0, 97) + "...")
                        + "\"");

                // Extract coordinates from GET request string.
                int pos = request.indexOf("&");
                if (pos != -1) {
                    request = request.substring(6, pos);
                }
                System.out.println(request);
                String[] parts = request.split(",");
                float sourceLat = Float.parseFloat(parts[0]);
                float sourceLng = Float.parseFloat(parts[1]);
                double targetLat = Double.parseDouble(parts[2]);
                double targetLng = Double.parseDouble(parts[3]);

                Coordinate coordinate = new Coordinate(targetLat, targetLng);
                Node closestNode = roadNetwork.findClosestNode(new Node(Integer.MAX_VALUE, coordinate));
                ShortestPath path = dijkstraAlgorithm.readPathTo(closestNode);


                // Send JSONP results string back to client.
                String jsonp = "redrawLineServerCallback({\n" +
                        "  path: [" + path.export(new SimplePathExporter()) + "]\n" + "})\n";
                String answer = "HTTP/1.0 200 OK\r\n"
                        + "Content-Length: " + jsonp.length() + "\r\n"
                        + "Content-Type: application/javascript" + "\r\n"
                        + "Connection: close\r\n"
                        + "\r\n" + jsonp;

                out = new PrintWriter(client.getOutputStream(), true);
                out.println(answer);
                out.close();
                in.close();
                client.close();
            }


        } catch (IOException | SAXException e) {
            e.printStackTrace();                                //Input/output errors management
        }

    }
}
