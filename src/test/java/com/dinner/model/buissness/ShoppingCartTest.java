package com.dinner.model.buissness;

import com.dinner.model.domain.Product;
import com.dinner.model.domain.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tomek on 04-Feb-17.
 */
public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    @Before
    public void init(){
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void addProductTest(){
        shoppingCart.addProduct(new Product("This is some desc",20.00,new byte[1]));
        Assert.assertTrue(shoppingCart.getProducts().size()>0);
    }
    @Test
    public void getTotalTest(){
        shoppingCart.addProduct(new Product("This is some desc",20.00,new byte[1]));
        shoppingCart.addProduct(new Product("This is some desc",30.00,new byte[1]));
        shoppingCart.addProduct(new Product("This is some desc",40.00,new byte[1]));

        Assert.assertEquals(90.00,shoppingCart.getTotal(),0.0001);
    }
    @Test
    public void deleteProductTest(){
        Product productToDelete = new Product("This is some desc", 20.00, new byte[1]);
        shoppingCart.addProduct(productToDelete);
        shoppingCart.addProduct(new Product("This is some desc",30.00,new byte[1]));
        Assert.assertEquals(shoppingCart.getProducts().size(),2);

        shoppingCart.removeProduct(productToDelete);

        Assert.assertEquals(shoppingCart.getProducts().size(),1);
    }
}
