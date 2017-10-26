package com.dinner.respository;

import com.dinner.model.domain.product.Product;
import com.dinner.model.value.objects.Money;
import com.dinner.model.value.objects.ProductListWrapper;
import com.dinner.model.value.objects.transfer.Exporter;
import com.dinner.model.value.objects.transfer.JsonExporter;
import com.dinner.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryIntegrationTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void shouldReturnTrueOnJsonProductListValidation(){
        Exporter exporter = new JsonExporter();

        List<Product> products = productsRepository.findAll();


        ProductListWrapper productListWrapper = new ProductListWrapper(products);

        String export = productListWrapper.export(productListWrapper.new JsonListExporter());
        Assert.assertTrue(exporter.isValid(export));


    }
}
