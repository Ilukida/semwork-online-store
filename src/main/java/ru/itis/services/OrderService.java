package ru.itis.services;

import ru.itis.models.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    List<Order> getAllOrdersByUser(Integer userId);
}
