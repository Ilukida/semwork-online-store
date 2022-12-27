package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.models.Product;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderForm {
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
