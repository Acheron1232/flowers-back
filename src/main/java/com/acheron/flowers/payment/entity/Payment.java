package com.acheron.flowers.payment.entity;

import com.acheron.flowers.security.entity.User;

import java.util.Currency;
import java.util.List;

public class Payment {
    private List<Long> productsId;
    private Currency currency;
    private String description;
    private String order_id;
    private User user;
    private Status status;
}
