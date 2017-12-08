package com.graph.model;

public class Coordinate {
    private Double latitude;
    private Double longitude;

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distance(Coordinate coordinate) {
        double lat1 = this.latitude;
        double lon1 = this.longitude;

        double lat2 = coordinate.latitude;
        double lon2 = coordinate.longitude;

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c ; // convert to km

        return distance/1000;
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
