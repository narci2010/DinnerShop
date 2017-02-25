package com.dinner.model.business;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Tomek on 20-Feb-17.
 */
@Data
public class OrderedProductsPK implements Serializable {
    @Id
    private Long userOrderId;
    @Id
    private Long productId;
}
