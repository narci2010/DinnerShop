/*
	Copyright 2014 Adrien PAVIE
	
	This file is part of BasicOSMParser.
	
	BasicOSMParser is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	
	BasicOSMParser is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with BasicOSMParser. If not, see <http://www.gnu.org/licenses/>.
 */

package com.basicosmparser.model;

/**
 * A node is an OSM element with coordinates.
 *
 * @author Adrien PAVIE
 */
public class Node extends Element {
//ATTRIBUTES
    /**
     * The latitude
     **/
    private double lat;
    /**
     * The longitude
     **/
    private double lon;

//CONSTRUCTOR

    /**
     * Default constructor
     *
     * @param id  The object ID
     * @param lat The latitude
     * @param lon The longitude
     */
    public Node(long id, double lat, double lon) {
        super(id);
        this.lat = lat;
        this.lon = lon;
    }

    //ACCESSORS
    @Override
    public String getId() {
        return "N" + id;
    }

    /**
     * @return the latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * @return the longitude
     */
    public double getLon() {
        return lon;
    }

//MODIFIERS

    /**
     * @param lat the new latitude
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @param lon the new longitude
     */
    public void setLon(double lon) {
        this.lon = lon;
    }


    public double distance(Node node) {

        double lat1 = this.lat;
        double lon1 = this.lon;

        double lat2 = node.lat;
        double lon2 = node.lon;

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c ; // convert to km

        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.lat, lat) != 0) return false;
        return Double.compare(node.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
