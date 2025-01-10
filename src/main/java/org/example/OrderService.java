package org.example;

import java.util.*;

public class OrderService {
    private final Map<UUID, Order> orders;

    public OrderService() {
        this.orders = new HashMap<>();
    }

    public UUID placeOrder(List<Product> products) {
        Order order = new Order(products);
        orders.put(order.getOrderId(), order);
        return order.getOrderId();
    }

    public String trackOrder(UUID orderId) {
        Order order = orders.get(orderId);
        return (order != null) ? order.getStatus() : "Order not found";
    }

    public boolean returnOrder(UUID orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus("Returned");
            return true;
        }
        return false;
    }

    public Order repeatOrder(UUID orderId) {
        Order originalOrder = orders.get(orderId);
        if (originalOrder != null) {
            return new Order(originalOrder.getProducts());
        }
        return null;
    }
}
