package com.dinner.model.domain.product;

import com.dinner.model.value.objects.transfer.Exportable;
import com.dinner.model.value.objects.transfer.Exporter;
import com.dinner.model.value.objects.Money;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;

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
    public String export(Exporter exporter) {
        exporter.addFieldToExport("id", id.toString());
        exporter.addFieldToExport("description", description);
        exporter.addFieldToExport("price", price.toString());
        exporter.addFieldToExport("image",  DatatypeConverter.printBase64Binary(image));

        return exporter.toString();
    }
}
