package com.dinner.model.transfer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCartDTO {
    private String currency;
    private BigDecimal shipping;
    private BigDecimal tax;
    private BigDecimal taxRate;
    private Integer itemCount;
    private List<Item> items = new ArrayList<>();
}
