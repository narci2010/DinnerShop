package com.dinner.model.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tomek on 29-Jan-17.
 */
@Entity
@Table(name = "USER_ORDERS")
@Data
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateCreated;
    private Long userId;



}
