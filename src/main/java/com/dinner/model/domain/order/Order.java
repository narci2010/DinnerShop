package com.dinner.model.domain.order;

import com.dinner.model.domain.product.Product;
import com.dinner.model.domain.user.User;
import com.dinner.model.value.objects.Money;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomek on 29-Jan-17.
 */
@Entity
@ToString
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "denomination", column = @Column(name = "amount")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "currency"))
    })
    private Money totalCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreated;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pk.order")
    private Set<OrderedProducts> orderedProducts = new HashSet<>();


    public Order(Money totalCost, Date dateCreated, User user) {
        this.totalCost = totalCost;
        this.dateCreated = dateCreated;
        this.user = user;
    }

    public void addProductToOrder(Product product, Integer quantity) {
        this.orderedProducts.add(new OrderedProducts(product, quantity, this));
    }
}
