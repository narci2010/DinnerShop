package com.dinner.model.transfer;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String description;
    private String currency;
    private BigDecimal price;
    private byte[] image;
}
