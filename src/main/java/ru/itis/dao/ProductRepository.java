package ru.itis.dao;

import ru.itis.models.Helper;
import ru.itis.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> getProductByCategory(Integer categoryId);
    Optional<Product> getProduct(Integer productId);
    List<Product> getAllProductsInBasket(Integer userId);
    void saveProductInBasket(Integer userId, Integer productId);
    List<Product> getAllProductsFormLiked(Integer userId);
    void saveProductInLiked(Integer userId, Integer productId);
    void addPhotoForProduct(Integer productId, Integer fileId);
    void deleteFromBasket(Integer productId, Integer userId);
    void deleteFromLiked(Integer productId, Integer userId);
    Optional<Helper> findInBasket(Integer productId, Integer userId);
    Optional<Helper> findInLiked(Integer productId, Integer userId);
    List<Product> getAllProductsInOrder(Integer orderId);
    void deleteAllBasket(Integer userId);
}
