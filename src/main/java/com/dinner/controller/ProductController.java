package com.dinner.controller;

import com.dinner.model.business.Product;
import com.dinner.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tomek on 08-Feb-17.
 */
@RestController
public class ProductController {
    @Autowired
    private ProductsRepository productsRepository;
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> products() {
        return productsRepository.findAll();
    }
}
