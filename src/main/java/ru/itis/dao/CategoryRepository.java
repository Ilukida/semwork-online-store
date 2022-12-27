package ru.itis.dao;

import ru.itis.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getCategories();
    Optional<Category> getCategoryById(Integer id);
}
