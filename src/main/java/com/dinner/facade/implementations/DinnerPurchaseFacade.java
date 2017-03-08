package com.dinner.facade.implementations;

import com.dinner.facade.interfaces.PurchaseFacade;
import com.dinner.facade.interfaces.ShoppingCartFacade;
import com.dinner.service.interfaces.AccountService;
import com.dinner.service.interfaces.OrderService;
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
    private ShoppingCartFacade shoppingCartFacade;

    private Long lastOrderId;

    @Override
    public void purchaseProducts() {
        this.lastOrderId = orderService.placeOrder();
        accountService.updateUserAccount();
        shoppingCartFacade.clearShoppingCart();

    }

    @Override
    public Long getLastOrderId() {
        return this.lastOrderId;
    }
}
