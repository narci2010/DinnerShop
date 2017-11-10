package com.dinner.factory.order;


import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.order.Order;
import com.dinner.model.transfer.Item;
import com.dinner.model.transfer.ShoppingCartDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderFactoryTest {

    @Autowired
    private OrderFactory orderFactory;

    @Autowired
    private ShoppingCartFactory shoppingCartFactory;

    private ShoppingCart shoppingCart;

    @Before
    public void init(){
        List<Item> items = new ArrayList<>(1);
        items.add(new Item(1L,"testName",1,new BigDecimal(12)));

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setItems(items);
        shoppingCartDTO.setCurrency("EUR");
        shoppingCartDTO.setItemCount(1);


        shoppingCart = shoppingCartFactory.createShoppingCart(shoppingCartDTO);
    }

    @Test
    public void shouldPlaceOrder(){
        //TODO mock User
/*        Order order = orderFactory.createOrder(shoppingCart);
        System.out.println(order.toString());*/
    }
}
