package ru.itis.services.impl;

import ru.itis.dao.OrderRepository;
import ru.itis.models.Order;
import ru.itis.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrdersByUser(Integer userId) {
        return orderRepository.getAllOrdersByUser(userId);
    }
}
