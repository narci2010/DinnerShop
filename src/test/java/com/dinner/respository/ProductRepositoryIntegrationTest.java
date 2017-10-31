package com.dinner.respository;

import com.dinner.model.domain.product.Product;
import com.dinner.repository.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryIntegrationTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void shouldReturnTrueOnJsonProductListValidation(){

        List<Product> products = productsRepository.findAll();





    }
}
