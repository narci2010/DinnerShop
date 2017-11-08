package com.dinner.model.value.objects;

import com.dinner.annotations.domain.ValueObject;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@ValueObject
@EqualsAndHashCode
@Embeddable
public class Money implements Serializable {
    public static final Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");
    public static final Money ZERO = new Money(BigDecimal.ZERO);


    private BigDecimal denomination;
    private String currencyCode;

    public Money() {
    }

    public Money(BigDecimal denomination, Currency currency) {
        this(denomination, currency.getCurrencyCode());
    }

    public Money(BigDecimal denomination) {
        this(denomination, DEFAULT_CURRENCY);
    }

    public Money(double denomination, Currency currency) {
        this(new BigDecimal(denomination), currency.getCurrencyCode());
    }

    public Money(double denomination, String currencyCode) {
        this(new BigDecimal(denomination), currencyCode);
    }

    public Money(double denomination) {
        this(denomination, DEFAULT_CURRENCY);
    }

    private Money(BigDecimal denomination, String currencyCode) {
        this.denomination = denomination.setScale(2, RoundingMode.HALF_EVEN);
        this.currencyCode = currencyCode;
    }

    public Money multiplyBy(double multiplier) {
        return multiplyBy(new BigDecimal(multiplier));
    }

    public Money multiplyBy(BigDecimal multiplier) {
        return new Money(denomination.multiply(multiplier), currencyCode);
    }

    public Money add(Money money) {
        checkCurrencyCompatibility(money);
        return new Money(denomination.add(money.denomination), currencyCode);
    }

    public Money subtract(Money money) {
        checkCurrencyCompatibility(money);
        return new Money(denomination.subtract(money.denomination), currencyCode);
    }

    public boolean greaterThan(Money other) {
        return denomination.compareTo(other.denomination) > 0;
    }

    public boolean lessThan(Money other) {
        return denomination.compareTo(other.denomination) < 0;
    }

    public boolean hasCompatibleCurrency(Money money){
        checkCurrencyCompatibility(money);
        return true;
    }

    private void checkCurrencyCompatibility(Money money) {
        if (incompatibleCurrency(money)) {
            throw new IllegalArgumentException("Currency mismatch : " + currencyCode + " -> " + money.currencyCode);
        }
    }

    private boolean incompatibleCurrency(Money money) {
        return !currencyCode.equals(money.currencyCode);
    }

    @Override
    public String toString() {
        return String.format("%0$.2f %s", denomination, currencyCode);

    }
}
