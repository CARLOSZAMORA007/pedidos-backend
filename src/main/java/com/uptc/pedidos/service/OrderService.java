package com.uptc.pedidos.service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import com.uptc.pedidos.client.BeverageClient;
import com.uptc.pedidos.model.Order;
import com.uptc.pedidos.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BeverageClient beverageClient;

    public Order createOrder(Order order) {
        boolean exists = beverageClient.checkDrinkExists(order.getDrinkName(), order.getSize());
        order.setAccepted(exists);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll().stream()
            .filter(Order::isAccepted)
            .toList();
    }
    
}