package com.dinner.model.value.objects;

import com.dinner.annotations.domain.ValueObject;
import com.dinner.model.domain.product.Product;
import com.dinner.model.domain.transfer.Exportable;
import com.dinner.model.domain.transfer.Exporter;
import com.dinner.model.domain.transfer.JsonExporter;

import java.util.List;

@ValueObject
public class ProductListWrapper implements Exportable {

    private List<Product> productList;

    public ProductListWrapper(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String export(Exporter exporter) {
        StringBuilder stringBuilder = new StringBuilder(10000);
        stringBuilder.append("[");

        for (Product product : productList) {
            stringBuilder.append(product.export(new JsonExporter()));
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();

    }
}
