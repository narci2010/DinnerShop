package com.navigation;

public enum RoadTypeSpeed {
    motorway(110),
    trunk(110),
    primary(70),
    secondary(60),
    tertiary(50),
    motorway_link(50),
    trunk_link(50),
    primary_link(50),
    secondary_link(50),
    road(40),
    unclassified(40),
    residential(30),
    unsurfaced(30),
    living_street(10),
    service(5);

    private int velocity;

    RoadTypeSpeed(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }
}
