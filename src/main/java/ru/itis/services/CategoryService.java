package ru.itis.services;

import ru.itis.models.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Integer id);
    Map<Integer, String> getCategoryMap();
}
