package com.dinner.service.implementations;

import com.dinner.model.domain.OrderedProducts;
import com.dinner.model.domain.Product;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.UserOrder;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.repository.OrderedProductsRepository;
import com.dinner.repository.UserOrdersRepository;
import com.dinner.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Tomek on 21-Feb-17.
 */
public class DinnerOrderService implements OrderService {

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private ShoppingCart shoppingCart;
    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private OrderedProductsRepository orderedProductsRepository;


    @Override
    public Long placeOrder() {
        UserOrder userOrder = addOrder();
        addOrderedItems(userOrder);
       return userOrder.getId();
    }

    private UserOrder addOrder() {
        UserOrder userOrder = new UserOrder();
//        userOrder.setUserId(authenticationFacade.getAuthentication().getId());
        userOrder.setAmount(BigDecimal.valueOf(shoppingCart.getTotal()));
        userOrder.setDateCreated(new Timestamp(new Date().getTime()));
        userOrdersRepository.save(userOrder);
        return userOrder;
    }

    private void addOrderedItems(UserOrder userOrder) {
        for (Product product : shoppingCart.getProducts()) {
            OrderedProducts orderedProducts = new OrderedProducts();
            orderedProducts.setUserOrderId(userOrder.getId());
            orderedProducts.setProductId(product.getId());
            orderedProductsRepository.save(orderedProducts);
        }
    }
}
