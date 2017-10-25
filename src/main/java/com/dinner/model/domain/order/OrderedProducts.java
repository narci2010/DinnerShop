package com.dinner.model.domain.order;

import com.dinner.model.domain.product.Product;

import javax.persistence.*;

/**
 * Created by Tomek on 20-Feb-17.
 */
@Entity
@Table(name = "ORDERED_PRODUCTS")
public class OrderedProducts {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "userOrderId", column = @Column(name = "user_order_id", nullable = false)),
            @AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false))})
    OrderedProductsPK orderedProductsPK;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    public OrderedProducts(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public OrderedProducts() {
    }
}
