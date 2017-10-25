package com.dinner.service.application.interfaces;

import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.order.Order;
import org.springframework.stereotype.Service;

/**
 * Created by Tomek on 21-Feb-17.
 */
@Service
public interface OrderService {
    Order placeOrder(ShoppingCart shoppingCart);
}
