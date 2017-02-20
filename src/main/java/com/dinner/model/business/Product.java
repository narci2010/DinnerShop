package com.dinner.model.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Tomek on 28-Jan-17.
 */
@Entity
@Table(name = "Products")
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Getter private String description;
    @Getter private Double price;
    @Lob
    @Getter private byte [] image;

    public Product() {
    }

    public Product(String description, Double price, byte[] image) {
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
