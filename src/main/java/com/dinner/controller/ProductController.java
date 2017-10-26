package com.dinner.controller;

import com.dinner.model.domain.product.Product;
import com.dinner.model.value.objects.ProductListWrapper;
import com.dinner.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Tomek on 08-Feb-17.
 */
@RestController
public class ProductController {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

/*    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> products() {

        return productsRepository.findAll();
    }*/

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public void products(HttpServletResponse res) {


        List<Product> all = productsRepository.findAll();
        ProductListWrapper productListWrapper = new ProductListWrapper(all);

        try (PrintWriter printWriter = res.getWriter()) {
            printWriter.println(productListWrapper.export(productListWrapper.new JsonListExporter()));
        } catch (IOException e) {
            //TODO logger
            System.out.println(e.getMessage());
        }


    }

}
