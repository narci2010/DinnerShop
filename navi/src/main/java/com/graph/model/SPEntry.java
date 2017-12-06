package com.graph.model;

import lombok.Data;


@Data
public class SPEntry implements Comparable<SPEntry> {

    private int nodeId;
    private Cost cost;
    private SPEntry parent;


    public int compareTo(SPEntry o) {
        return cost.compareTo(o.cost);
    }
}
