package com.dinner.facade.implementations;

import com.dinner.facade.interfaces.PurchaseFacade;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.service.application.implementations.ShoppingCartService;
import com.dinner.service.application.interfaces.AccountService;
import com.dinner.service.application.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomek on 21-Feb-17.
 */
public class DinnerPurchaseFacade implements PurchaseFacade {

    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    private Long lastOrderId;

    @Override
    public void purchaseProducts() {
        //TODO mock
        orderService.placeOrder(new ShoppingCart());
        accountService.updateUserAccount();
        shoppingCartService.clear();

    }

    @Override
    public Long getLastOrderId() {
        return this.lastOrderId;
    }
}
