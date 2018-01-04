package com.graph.weighting;

import com.graph.model.Arc;
import com.graph.model.Cost;

public class SimpleWeighing implements Weighting {
    @Override
    public Cost calculateWeight(Arc arc) {
        return arc.getCost();
    }
}
