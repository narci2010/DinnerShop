package com.dinner.model.domain.product;

import com.dinner.model.value.objects.Money;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Tomek on 28-Jan-17.
 */
@Entity
@Table(name = "Products")
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String description;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "denomination", column = @Column(name = "price")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "currency"))
    })
    @Getter
    private  Money price;
    @Lob
    private  byte[] image;


    protected Product() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!description.equals(product.description)) return false;
        return price.equals(product.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
