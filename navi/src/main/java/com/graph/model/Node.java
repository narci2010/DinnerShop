package com.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private Integer id;
    private Double latitude;
    private Double longitude;
    private List<Arc> outgoingArcs = new ArrayList<>();

    public Node(Integer id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Node(String id, double lat, double lon) {
        this.id = Integer.valueOf(id.substring(1));
        this.latitude = lat;
        this.longitude = lon;
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
