package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Order {
    private Integer id;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String cardOwner;
    private String cartNumber;
    private String expiration;
    private String cvv;
    private Integer userId;
    private Integer amount;
    private List<Product> productList;
}
