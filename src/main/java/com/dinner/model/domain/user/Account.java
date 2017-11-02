package com.dinner.model.domain.user;

import com.dinner.model.value.objects.Money;
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


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "denomination", column = @Column(name = "money")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "currency"))
    })
    protected Money money;

    protected Account() {
    }

    public Account(Money money) {
        this.money = money;
    }


    protected boolean withdraw(Money cash) {

        if (cash.greaterThan(this.money)) {
            return false;
        }
        this.money = this.money.subtract(cash);
        return true;
    }

    protected void returnMoney(Money money) {
        this.money = this.money.add(money);

    }

}
