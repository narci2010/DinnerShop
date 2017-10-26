package com.dinner.model.value.objects.transfer;

import com.dinner.model.domain.product.Product;
import com.dinner.model.value.objects.Money;
import com.dinner.model.value.objects.ProductListWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonExporterTest {
    private Exporter exporter = new JsonExporter();
    @Test
    public void shouldReturnValidJson(){
        exporter.addFieldToExport("id","1");
        exporter.addFieldToExport("test","2");

        Assert.assertTrue(exporter.isValid(exporter.toString()));
    }

    @Test
    public void shouldReturnFalseOnJsonValidation(){
        String json = "{\n" +
                "\"name\":\"John\" \n" +
                "\"age\":30,\n" +
                "\"cars\":[ \"Ford\", \"BMW\", \"Fiat\" ]\n" +
                "}";
        Assert.assertFalse(exporter.isValid(json));
    }

    @Test
    public void shouldReturnTrueOnJsonValidation(){
        String json = "{\n" +
                "\"name\":\"John\", \n" +
                "\"age\":30,\n" +
                "\"cars\":[ \"Ford\", \"BMW\", \"Fiat\" ]\n" +
                "}";
        Assert.assertTrue(exporter.isValid(json));
    }


}
