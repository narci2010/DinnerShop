package com.dinner.respository;

import com.dinner.model.business.OrderedProducts;
import com.dinner.model.business.Product;
import com.dinner.model.business.UserOrder;
import com.dinner.repository.OrderedProductsRepository;
import com.dinner.repository.UserOrdersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by Tomek on 20-Feb-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderedProductsRepositoryIntegrationTest {

    @Autowired
    OrderedProductsRepository orderedProductsRepository;


/*    @Test
    public void addFakeOrders(){
        OrderedProducts orderedProducts = new OrderedProducts();
        orderedProducts.setProductId((long) 1);
        orderedProducts.setUserOrderId((long) 1);
        orderedProductsRepository.save(orderedProducts);
    }*/



}
