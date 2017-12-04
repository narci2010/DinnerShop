package com.graph.model;

public class Cost implements Comparable<Cost> {
    public Cost(int seconds) {
        this.seconds = seconds;
    }

    private int seconds;

    public Cost addCost(Cost cost) {
        return new Cost(this.seconds + cost.seconds);

    }

    public boolean greaterThan(Cost cost) {
        return this.compareTo(cost) > 0;
    }

    public Cost subtract(Cost cost) {
        return new Cost(this.seconds - cost.seconds);
    }

    public static Cost abs(Cost cost) {
        int abs = Math.abs(cost.seconds);
        return new Cost(abs);
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
