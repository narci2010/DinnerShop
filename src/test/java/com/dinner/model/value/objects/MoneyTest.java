package com.dinner.model.value.objects;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyTest {

    private Money testMoney = new Money(new BigDecimal(10), Currency.getInstance("PLN"));
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldReturnAddedMoney() {
        Money addedMoney = testMoney.add(new Money(new BigDecimal(10), Currency.getInstance("PLN")));
        assertEquals("20,00 PLN", addedMoney.toString());
    }

    @Test
    public void shouldThrowCurrencyMismatchExceptionWhenAdding() {
        exception.expect(IllegalArgumentException.class);
        Money addedMoney = testMoney.add(new Money(new BigDecimal(10), Currency.getInstance("EUR")));

    }

    @Test
    public void shouldMultiplyMoneyByMultiplier() {
        Money money = testMoney.multiplyBy(5);
        assertEquals("50,00 PLN", money.toString());
    }

    @Test
    public void shouldSubtractMoney() {
        Money money = testMoney.subtract(new Money(new BigDecimal(5), Currency.getInstance("PLN")));
        assertEquals("5,00 PLN", money.toString());
    }

    @Test
    public void shouldThrowCurrencyMismatchExceptionWhenSubtract() {
        exception.expect(IllegalArgumentException.class);
        testMoney.subtract(new Money(new BigDecimal(5), Currency.getInstance("EUR")));

    }

    @Test
    public void shouldReturnLessThan() {
        Money money = new Money(2,"PLN");
        assertTrue(money.lessThan(testMoney));
    }
    @Test
    public void shouldReturnGtThan() {
        Money money = new Money(2,"PLN");
        assertTrue(testMoney.greaterThan(money));
    }

}
