package com.graph.weighting;

import com.graph.model.Arc;
import com.graph.model.Cost;

public interface Weighting {
    Cost calculateWeight(Arc arc);
}
