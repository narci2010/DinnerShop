package com.dinner.respository;

import com.dinner.model.domain.Product;
import com.dinner.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryIntegrationTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void testFindAllProducts() {
        List<Product> products = productsRepository.findAll();
        Assert.assertTrue(products.size() > 0);

    }

    @Test
    public void testFindById() {
        Product product = productsRepository.findOne((long) 1);
        Assert.assertTrue(product.getId() == 1);
    }

}
