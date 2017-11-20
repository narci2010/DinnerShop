
import com.basicosmparser.controller.OSMParser;
import com.basicosmparser.model.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        OSMParser p = new OSMParser();						//Initialization of the parser
        File osmFile = new File("C:\\Users\\TomaszZielichowski\\Documents\\Projects\\DinnerShop\\navi\\src\\main\\resources\\map.osm");	//Create a file object for your OSM XML file

        try {

            Map<String,Element> result = p.parse(osmFile);//Parse OSM data, and put result in a Map object


            result.entrySet().forEach(stringElementEntry -> System.out.println(stringElementEntry.getKey() + " " + stringElementEntry.getValue()));

        } catch (IOException | SAXException e) {
            e.printStackTrace();								//Input/output errors management
        }

    }
}
