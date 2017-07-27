package com.dinner.repository;

import com.dinner.model.domain.OrderedProducts;
import com.dinner.model.domain.OrderedProductsPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Tomek on 20-Feb-17.
 */
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, OrderedProductsPK> {

    List<OrderedProducts> findAllOrderedProductsByUserOrderId(Long id);
}
