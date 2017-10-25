package com.dinner.model.domain.product;

import com.dinner.model.domain.order.OrderedProducts;
import com.dinner.model.domain.transfer.Exportable;
import com.dinner.model.domain.transfer.Exporter;
import com.dinner.model.value.objects.Money;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomek on 28-Jan-17.
 */
@Entity
@Table(name = "Products")
@EqualsAndHashCode
public class Product implements Exportable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "denomination", column = @Column(name = "price")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "currency"))
    })
    private Money price;
    @Lob
    private byte[] image;


    public Product() {
    }

    public Product(String description, Money price, byte[] image) {
        this.description = description;
        this.price = price;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }


    @Override
    @Autowired
    public String export(Exporter exporter) {
        exporter.addFieldToExport("id", id.toString());
        exporter.addFieldToExport("description", description);
        exporter.addFieldToExport("price", price.toString());
        exporter.addFieldToExport("image",  DatatypeConverter.printBase64Binary(image));

        return exporter.toString();
    }
}
