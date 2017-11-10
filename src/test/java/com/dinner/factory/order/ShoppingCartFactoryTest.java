package com.dinner.factory.order;

import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.transfer.Item;
import com.dinner.model.transfer.ShoppingCartDTO;
import com.dinner.model.value.objects.Money;
import org.junit.Assert;
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
public class ShoppingCartFactoryTest {

    @Autowired
    private ShoppingCartFactory shoppingCartFactory;

    @Test
    public void shouldCreateShoppingCart(){

        List<Item> items = new ArrayList<>(1);
        items.add(new Item(1L,"testName",1,new BigDecimal(12)));

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setItems(items);
        shoppingCartDTO.setCurrency("EUR");
        shoppingCartDTO.setItemCount(1);


        ShoppingCart shoppingCart = shoppingCartFactory.createShoppingCart(shoppingCartDTO);
        Assert.assertEquals(new Money(12, "EUR"), shoppingCart.getTotal());
        Assert.assertEquals(shoppingCart.getProducts().size(),1);
    }
}
