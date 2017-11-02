package com.dinner.model.buissness;

import com.dinner.model.domain.product.Product;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.value.objects.Money;
import com.dinner.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ShoppingCartTest {
    ShoppingCart shoppingCart;

    @Autowired
    private ProductsRepository productsRepository;
    private Product testProduct;

    @Before
    public void init(){
        testProduct = productsRepository.save(new Product("desc",new Money(20.00,"PLN"),new byte[1]));
        shoppingCart = new ShoppingCart();
    }



    @Test
    public void addProductTest(){

        shoppingCart.addProduct(testProduct,1);
        Assert.assertTrue(shoppingCart.getProducts().size()>0);

    }
    @Test
    public void getTotalTest(){

        shoppingCart.addProduct(testProduct,1);
        shoppingCart.addProduct(testProduct,2);
        shoppingCart.addProduct(testProduct,1);

        Assert.assertEquals(new Money(80.00,"PLN"),shoppingCart.getTotal());
    }

    @Test
    public void shouldUpdateShoppingCartItemQuantity(){

        shoppingCart.addProduct(testProduct,1);
        shoppingCart.addProduct(testProduct,2);
        Integer quantity = shoppingCart.getProducts().get(testProduct);
        Assert.assertEquals(new Integer(3), quantity);
    }

    @Test
    public void shouldReturnTrueOnHasProduct(){
        shoppingCart.addProduct(testProduct,2);
        Assert.assertTrue(shoppingCart.hasProduct(testProduct));
    }
}
