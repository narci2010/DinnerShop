package com.dinner.model.domain.order;

import com.dinner.model.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Tomek on 20-Feb-17.
 */
@Data
@Embeddable
public class OrderedProductsPK implements Serializable {
    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Product product;


}
