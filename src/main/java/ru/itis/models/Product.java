package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private String maker;
    private Integer price;
    private String description;
    private Integer categoryId;
    private Integer pictureId;
}
