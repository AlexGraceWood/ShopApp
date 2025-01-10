package org.example;

import java.util.*;

public class Cart {
    private final List<Product> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Product product) {
        items.add(product);
    }

    public void removeFromCart(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }
}


