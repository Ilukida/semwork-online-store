package ru.itis.services;

import ru.itis.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByCategory(Integer categoryId);
    Product save(Product product);
    Product getProductById(Integer productId);
    List<Product> getAllProductsInBasket(Integer userId);
    void saveProductInBasket(Integer userId, Integer productId);
    List<Product> getAllProductsFormLiked(Integer userId);
    void saveProductInLiked(Integer userId, Integer productId);
    void deleteFromBasket(Integer productId, Integer userId);
    void deleteFromLiked(Integer productId, Integer userId);
    Integer amount(Integer userId);
    void deleteAllBasket(Integer userId);
    List<Product> getAllProductsInOrder(Integer orderId);
}
