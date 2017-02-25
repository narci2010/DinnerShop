package com.dinner.repository;

import com.dinner.model.business.OrderedProducts;
import com.dinner.model.business.OrderedProductsPK;
import com.dinner.model.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Tomek on 20-Feb-17.
 */
public interface OrderedProductsRepository extends JpaRepository<OrderedProducts, OrderedProductsPK> {

    List<OrderedProducts> findAllOrderedProductsByUserOrderId(Long id);
}
