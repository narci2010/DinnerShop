package com.graph.model;

import com.export.CoordinateExporter;
import com.export.Exportable;
import com.export.Exporter;

import java.util.*;

public class Node implements Distanceable<Node>, Exportable {

    private Integer id;
    private Coordinate coordinate;
    private List<Arc> outgoingArcs = new ArrayList<>();

    public Node(Integer id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
    }

    public Node(Integer id, Double latitude, Double longitude) {
        this.id = id;
        this.coordinate = new Coordinate(latitude, longitude);

    }

    public Node(String id, double lat, double lon) {
        this.id = Integer.valueOf(id.substring(1));
        this.coordinate = new Coordinate(lat, lon);

    }


    public void addOutgoingArcs(Arc arc) {
        outgoingArcs.add(arc);
    }

    public List<Arc> getOutgoingArcs() {
        return Collections.unmodifiableList(outgoingArcs);
    }

    //own id if this suppose to have sens
    public Integer getId() {
        return id;
    }

    @Override
    public String export(Exporter exporter) {
        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("coordinate", coordinate.export(new CoordinateExporter()));
        propertiesMap.put("id", String.valueOf(id));
        propertiesMap.put("outgoingArcs", outgoingArcs.toString());

        return exporter.export(propertiesMap);
    }

    @Override
    public double distance(Node node) {
        return this.coordinate.distance(node.coordinate);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return id != null ? id.equals(node.id) : node.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                "} : " + outgoingArcs
                ;

    }

}
