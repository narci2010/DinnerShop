package com.dinner.model.business;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Tomek on 29-Jan-17.
 */
@Entity
@Table(name = "USERS_ORDERS")
@Data
public class UserOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private Timestamp dateCreated;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany
    private Set<Product> orderedProducts;
    public void addProductToOrderList(Product product){
        orderedProducts.add(product);
    }


}
