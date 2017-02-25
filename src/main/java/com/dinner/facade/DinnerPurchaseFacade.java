package com.dinner.facade;

import com.dinner.service.AccountService;
import com.dinner.service.DinnerAccountService;
import com.dinner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomek on 21-Feb-17.
 */
public class DinnerPurchaseFacade implements PurchaseFacade {

    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    ShoppingCartFacade shoppingCartFacade;

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
