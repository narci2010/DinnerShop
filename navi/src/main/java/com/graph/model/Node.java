package com.graph.model;

public class Node {

    public Node(Integer id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Integer id;
    private Double latitude;
    private Double longitude;

    public Node(String id, double lat, double lon) {
        this.id = Integer.valueOf(id.substring(1));
        this.latitude =  lat;
        this.longitude =  lon;
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
                '}';
    }
}
