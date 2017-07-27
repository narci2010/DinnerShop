package com.dinner.model.domain.user;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Tomek on 29-Jan-17.
 */
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double money;

    public Account() {
    }

    public Account(Double money) {
        this.money = money;
    }


    public boolean withdraw(Double cash) {
        if (cash > this.money) {
            return false;
        }
        this.money -= cash;
        return true;
    }

    public void returnCash(Double cash) {
        this.money += cash;
    }

}
