package com.graph.model;

public class Cost {
    public Cost(int seconds) {
        this.seconds = seconds;
    }

    private int seconds;

    @Override
    public String toString() {
        return "Cost{" +
                "seconds=" + seconds +
                '}';
    }
}
