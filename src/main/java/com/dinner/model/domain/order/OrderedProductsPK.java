package com.dinner.model.domain.order;

import com.dinner.model.domain.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tomek on 20-Feb-17.
 */
@Data
@Embeddable
public class OrderedProductsPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;


}
