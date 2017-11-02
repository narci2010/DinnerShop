package com.dinner.model.domain.user;

import com.dinner.model.value.objects.Money;

//TODO better class name
public interface Payable {
    boolean withdraw(Money money);
    void returnMoney(Money money);
}
