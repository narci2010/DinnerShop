package com.dinner.model.domain.order;

import com.dinner.model.domain.product.Product;

import javax.persistence.*;

/**
 * Created by Tomek on 20-Feb-17.
 */
@Entity
@Table(name = "ORDERED_PRODUCTS")
@AssociationOverrides({
        @AssociationOverride(name = "pk.order",
                joinColumns = @JoinColumn(name = "user_order_id")),
        @AssociationOverride(name = "pk.product",
                joinColumns = @JoinColumn(name = "product_id"))})
public class OrderedProducts {

    @EmbeddedId
    OrderedProductsPK pk;

    private Integer quantity;

    public OrderedProducts(Product product, Integer quantity, Order order) {
        pk = new OrderedProductsPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;

    }

    protected OrderedProducts() {
    }
}
