package com.uptc.pedidos.repository;
import org.springframework.stereotype.Repository;

import com.uptc.pedidos.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Order save(Order order) {
        order.setId(idGenerator.getAndIncrement());
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }
}
