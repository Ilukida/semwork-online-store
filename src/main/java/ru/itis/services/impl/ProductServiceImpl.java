package ru.itis.services.impl;

import ru.itis.dao.ProductRepository;
import ru.itis.excptions.AlreadyExistsException;
import ru.itis.excptions.NotFoundException;
import ru.itis.models.Helper;
import ru.itis.models.Product;
import ru.itis.services.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.getProductByCategory(categoryId);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer productId) {
        Optional<Product> productOptional = productRepository.getProduct(productId);
        if(productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public List<Product> getAllProductsInBasket(Integer userId) {
        return productRepository.getAllProductsInBasket(userId);
    }

    @Override
    public void saveProductInBasket(Integer userId, Integer productId) {
        Optional<Helper> helperOptional = productRepository.findInBasket(productId, userId);
        if(!helperOptional.isPresent()) {
            productRepository.saveProductInBasket(userId, productId);
        } else {
            throw new AlreadyExistsException("Уже был добавлен!");
        }
    }

    @Override
    public List<Product> getAllProductsFormLiked(Integer userId) {
        return productRepository.getAllProductsFormLiked(userId);
    }

    @Override
    public void saveProductInLiked(Integer userId, Integer productId) {
        Optional<Helper> helperOptional = productRepository.findInLiked(productId, userId);
        if(!helperOptional.isPresent()) {
            productRepository.saveProductInLiked(userId, productId);
        } else {
            throw new AlreadyExistsException("Уже был добавлен!");
        }
    }

    @Override
    public void deleteFromBasket(Integer productId, Integer userId) {
        productRepository.deleteFromBasket(productId, userId);
    }

    @Override
    public void deleteFromLiked(Integer productId, Integer userId) {
        productRepository.deleteFromLiked(productId, userId);
    }

    @Override
    public Integer amount(Integer userId) {
        List<Product> products = productRepository.getAllProductsInBasket(userId);
        Integer amount = 0;
        for(Product product : products) {
            amount += product.getPrice();
        }
        return amount;
    }

    @Override
    public void deleteAllBasket(Integer userId) {
        productRepository.deleteAllBasket(userId);
    }

    @Override
    public List<Product> getAllProductsInOrder(Integer orderId) {
        return productRepository.getAllProductsInOrder(orderId);
    }

}
