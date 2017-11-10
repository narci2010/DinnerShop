package com.dinner.factory.product;

import com.dinner.annotations.domain.DomainFactory;
import com.dinner.model.domain.product.Product;
import com.dinner.model.transfer.ProductDTO;
import com.dinner.model.value.objects.Money;

import java.util.Currency;

@DomainFactory
public class ProductFactory {
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO.getDescription(), new Money(productDTO.getPrice(), Currency.getInstance(productDTO.getCurrency())), productDTO.getImage());
        return product;
    }
}
