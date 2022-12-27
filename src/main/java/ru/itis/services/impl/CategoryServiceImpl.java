package ru.itis.services.impl;

import lombok.Data;
import ru.itis.dao.CategoryRepository;
import ru.itis.excptions.NotFoundException;
import ru.itis.models.Category;
import ru.itis.services.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private Map<Integer, String> categoryMap = new HashMap<>();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        fillCategoryMap();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.getCategories();
    }

    @Override
    public Category getCategoryById(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.getCategoryById(id);
        if(categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    private void fillCategoryMap() {
        categoryMap.put(1, "Молочная продукция");
        categoryMap.put(2, "Колбасные изделия");
        categoryMap.put(3, "Крупы");
        categoryMap.put(4, "Морепродукты");
        categoryMap.put(5, "Напитки");
        categoryMap.put(6, "Овощи и зелень");
        categoryMap.put(7, "Сладкое");
        categoryMap.put(8, "Фрукты и ягоды");
        categoryMap.put(9, "Хлебобулочные изделия");
    }
}
