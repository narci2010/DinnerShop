package com.dinner.model.business;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Tomek on 20-Feb-17.
 */
@Entity
@Table(name = "ORDERED_PRODUCTS")
@IdClass(OrderedProductsPK.class)
@Data
public class OrderedProducts {
    @Id
    private Long userOrderId;
    @Id
    private Long productId;

}
