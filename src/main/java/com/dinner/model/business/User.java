package com.dinner.model.business;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tomek on 29-Jan-17.
 */
@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserOrders> userOrders;
}
