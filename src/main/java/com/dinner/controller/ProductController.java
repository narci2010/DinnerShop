package com.dinner.controller;

import com.dinner.factory.product.ProductFactory;
import com.dinner.model.domain.product.Product;
import com.dinner.model.transfer.ProductDTO;
import com.dinner.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Tomek on 08-Feb-17.
 */
@Controller
public class ProductController {
    private final ProductsRepository productsRepository;

    private final ProductFactory productFactory;

    @Autowired
    public ProductController(ProductsRepository productsRepository, ProductFactory productFactory) {
        this.productsRepository = productsRepository;
        this.productFactory = productFactory;
    }

    @ResponseBody
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> products() {
        return productsRepository.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        Product product = productFactory.createProduct(productDTO);
        productsRepository.save(product);

        return "index";
    }

}
