package com.graph.model;

public class Cost implements Comparable<Cost> {
    public Cost(int seconds) {
        this.seconds = seconds;
    }

    private int seconds;

    public Cost addCost(Cost cost) {
        return new Cost(this.seconds + cost.seconds);

    }

    @Override
    public String toString() {
        return "Cost{" +
                "seconds=" + seconds +
                '}';
    }

    @Override
    public int compareTo(Cost o) {
        return this.seconds - o.seconds;
    }
}
