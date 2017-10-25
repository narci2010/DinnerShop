package com.dinner.service.application.implementations;

import com.dinner.model.domain.order.Order;
import com.dinner.model.domain.order.OrderedProducts;
import com.dinner.model.domain.product.Product;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.repository.OrderedProductsRepository;
import com.dinner.repository.UserOrdersRepository;
import com.dinner.service.application.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * Created by Tomek on 21-Feb-17.
 */
public class DinnerOrderService implements OrderService {

    @Autowired
    private AuthenticationFacade authenticationFacade;
/*    @Autowired
    private ShoppingCart shoppingCart;*/
    @Autowired
    private UserOrdersRepository userOrdersRepository;

    @Autowired
    private OrderedProductsRepository orderedProductsRepository;



    @Override
    public Order placeOrder(ShoppingCart shoppingCart) {
        Order order = createOrder();
        Order savedOrder = userOrdersRepository.save(order);
        addProductsToOrder(savedOrder, shoppingCart);
        return userOrdersRepository.save(savedOrder);
    }

    private Order createOrder() {
        return new Order(new Timestamp(new Date().getTime()), authenticationFacade.getAuthentication());
    }

    private void addProductsToOrder(Order order, ShoppingCart shoppingCart) {
        Map<Product, Integer> products = shoppingCart.getProducts();
        products.entrySet().forEach(productEntry -> order.addProductToOrder(productEntry.getKey(), productEntry.getValue()));

    }
}
