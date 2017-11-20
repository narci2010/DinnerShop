package com.dinner.factory.order;

import com.dinner.annotations.domain.DomainFactory;
import com.dinner.model.domain.ShoppingCart;
import com.dinner.model.domain.product.Product;
import com.dinner.model.transfer.Item;
import com.dinner.model.transfer.ShoppingCartDTO;
import com.dinner.model.value.objects.Money;
import com.dinner.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@DomainFactory
public class ShoppingCartFactory {
    @Autowired
    private ProductsRepository productsRepository;

    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        Money moneyCurrencyCompatibilityHelperObject = new Money(0, shoppingCartDTO.getCurrency());
        Map<Product, Integer> productQuantity = new HashMap<>(shoppingCartDTO.getItemCount());

        for (Item item : shoppingCartDTO.getItems()) {
            Optional<Product> productOptional = Optional.ofNullable(productsRepository.getOne(item.getId()));

            productOptional.ifPresent(product -> {
                if (product.getPrice().hasCompatibleCurrency(moneyCurrencyCompatibilityHelperObject)) {
                    productQuantity.put(product, item.getQuantity());
                }
            });
        }
        return new ShoppingCart(productQuantity, shoppingCartDTO.getCurrency());

    }
}
