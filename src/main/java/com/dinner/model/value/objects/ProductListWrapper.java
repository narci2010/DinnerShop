package com.dinner.model.value.objects;

import com.dinner.annotations.domain.ValueObject;
import com.dinner.model.domain.product.Product;
import com.dinner.model.value.objects.transfer.Exportable;
import com.dinner.model.value.objects.transfer.Exporter;
import com.dinner.model.value.objects.transfer.JsonExporter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@ValueObject
public class ProductListWrapper implements Exportable {

    private List<Product> productList;

    public ProductListWrapper(List<Product> productList) {
        this.productList = productList;
    }

    public class JsonListExporter implements Exporter {
        private String toExport = "";

        @Override
        public void addFieldToExport(String filedName, String fieldValue) {
            JsonExporter exporter = new JsonExporter();
            exporter.addFieldToExport(filedName, fieldValue);
            toExport += exporter.toString();
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(10000);
            stringBuilder.append("[");

            for (Product product : productList) {
                stringBuilder.append(product.export(new JsonExporter()));
                stringBuilder.append(",");
            }

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");

            toExport = stringBuilder.toString();

            if (isValid(toExport)) {
                return toExport;
            } else {
                throw new RuntimeException("String is not valid Json format");
            }

        }

    }

    @Override
    public String export(Exporter exporter) {
        return exporter.toString();

    }
}
