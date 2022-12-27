package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductForm {
    private Integer id;
    private String name;
    private String maker;
    private Integer price;
    private String description;
    private Integer pictureId;
}
