package org.example;

import java.util.*;

public class Order {
    private final UUID orderId;
    private final List<Product> products;
    private String status;

    public Order(List<Product> products) {
        this.orderId = UUID.randomUUID();
        this.products = new ArrayList<>(products);
        this.status = "Processing";
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
