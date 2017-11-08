package com.dinner.service.application.implementations;

import com.dinner.annotations.domain.ApplicationService;
import com.dinner.factory.order.OrderFactory;
import com.dinner.factory.order.ShoppingCartFactory;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.order.Order;
import com.dinner.model.domain.user.User;
import com.dinner.model.security.AuthenticationFacade;
import com.dinner.model.transfer.ShoppingCartDTO;
import com.dinner.repository.OrderedProductsRepository;
import com.dinner.repository.UserOrdersRepository;
import com.dinner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomek on 21-Feb-17.
 */
@ApplicationService
public class DinnerOrderService {
    @Autowired
    private OrderFactory orderFactory;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOrdersRepository ordersRepository;
    @Autowired
    private ShoppingCartFactory shoppingCartFactory;

    public Order placeOrder(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartFactory.createShoppingCart(shoppingCartDTO);
        Order order = orderFactory.createOrder(shoppingCart);
        User user = authenticationFacade.getAuthentication();
        user.withdraw(shoppingCart.getTotal());

        Order ordered = ordersRepository.save(order);
        userRepository.save(user);

        return ordered;
    }


}
