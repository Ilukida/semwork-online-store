package ru.itis.dao;

import ru.itis.models.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    List<Order> getAllOrdersByUser(Integer userId);
}
