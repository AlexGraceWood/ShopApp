package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> filterProducts(String keyword, double minPrice, double maxPrice, String manufacturer) {
        return products.stream()
                .filter(p -> (keyword == null || p.getName().toLowerCase().contains(keyword.toLowerCase())) &&
                        (manufacturer == null || p.getManufacturer().equalsIgnoreCase(manufacturer)) &&
                        p.getPrice() >= minPrice &&
                        p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Product> recommendProducts() {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getRating).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}

