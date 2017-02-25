package com.dinner.model.business;

import com.dinner.facade.PurchaseFacade;
import com.dinner.repository.OrderedProductsRepository;
import com.dinner.repository.ProductsRepository;
import com.dinner.repository.UserOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomek on 22-Feb-17.
 */

@Component("orderDetails")
@Scope(value = "request")
public class OrderDetails {

    @Autowired
    PurchaseFacade purchaseFacade;

    @Autowired
    UserOrdersRepository userOrdersRepository;

    @Autowired
    OrderedProductsRepository orderedProductsRepository;

    @Autowired
    ProductsRepository productsRepository;

    private UserOrder userOrder;

    @PostConstruct
    private void init() {
        userOrder = userOrdersRepository.getOne(purchaseFacade.getLastOrderId());
    }

    public List<Product> getOrderedProduct() {
        List<Product> products =
                orderedProductsRepository.findAllOrderedProductsByUserOrderId(userOrder.getId())
                        .stream()
                        .map(orderedProducts -> productsRepository.getOne(orderedProducts.getProductId()))
                        .collect(Collectors.toList());

        return products;
    }

    public Double getAmount() {
        return userOrder.getAmount().doubleValue();
    }


    public Date getOrderDate() {
        return userOrder.getDateCreated();
    }
}
