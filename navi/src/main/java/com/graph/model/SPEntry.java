package com.graph.model;

import lombok.Data;


@Data
public class SPEntry implements Comparable<SPEntry> {

    private int nodeId;
    private Cost cost;
    private SPEntry parent;

    public SPEntry(int nodeId, Cost cost, SPEntry parent) {
        this.nodeId = nodeId;
        this.cost = cost;
        this.parent = parent;
    }

    public SPEntry() {
    }

    public int compareTo(SPEntry o) {
        return cost.compareTo(o.cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SPEntry spEntry = (SPEntry) o;

        return nodeId == spEntry.nodeId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nodeId;
        return result;
    }
}
