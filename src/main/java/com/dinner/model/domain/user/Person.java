package com.dinner.model.domain.user;

import javax.persistence.*;

/**
 * Created by Tomek on 11-Jul-17.
 */
@Entity
@Table(name = "Persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    protected Account account;

    public Person(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public Person() {
    }
}
