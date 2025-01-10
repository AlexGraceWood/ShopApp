package org.example;

import java.util.*;

public class ShopApp {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        Cart cart = new Cart();
        OrderService orderService = new OrderService();

        // Initial products
        List<Product> initialProducts = Arrays.asList(
                new Product("Laptop", "Dell", 800.00),
                new Product("Phone", "Apple", 999.99),
                new Product("Phone", "Samsung", 350.00),
                new Product("Phone", "Nokia", 767.00),
                new Product("Laptop", "Asus", 1200.00),
                new Product("Headphones", "Sony", 199.99)
        );
        initialProducts.forEach(productService::addProduct);

        System.out.println("Welcome to the Shop!");

        while (true) {
            System.out.println("\n1. View Products\n2. Filter Products\n3. Add to Cart\n4. View Cart\n5. Place Order\n6. Return Order\n7. Repeat Order\n8. Rate Product\n9. Get Recommendations\n10. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Available Products:");
                    productService.getProducts().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("Enter keyword (or leave blank): ");
                    String keyword = scanner.next();
                    System.out.print("Enter min price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter max price: ");
                    double maxPrice = scanner.nextDouble();
                    System.out.print("Enter manufacturer (or leave blank): ");
                    String manufacturer = scanner.next();

                    List<Product> filtered = productService.filterProducts(
                            keyword.equals("-") ? null : keyword,
                            minPrice, maxPrice,
                            manufacturer.equals("-") ? null : manufacturer
                    );
                    filtered.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter product name to add to cart: ");
                    String productName = scanner.next();
                    Optional<Product> product = productService.getProducts().stream()
                            .filter(p -> p.getName().equalsIgnoreCase(productName))
                            .findFirst();
                    if (product.isPresent()) {
                        cart.addToCart(product.get());
                        System.out.println(product.get().getName() + " added to cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                }
                case 4 -> {
                    System.out.println("Your Cart:");
                    cart.getItems().forEach(System.out::println);
                    System.out.println("Total: $" + cart.calculateTotal());
                }
                case 5 -> {
                    UUID orderId = orderService.placeOrder(cart.getItems());
                    System.out.println("Order placed! Your order ID: " + orderId);
                    cart.getItems().clear();
                }
                case 6 -> {
                    System.out.print("Enter order ID to return: ");
                    UUID orderId = UUID.fromString(scanner.next());
                    if (orderService.returnOrder(orderId)) {
                        System.out.println("Order returned successfully.");
                    } else {
                        System.out.println("Order not found.");
                    }
                }
                case 7 -> {
                    System.out.print("Enter order ID to repeat: ");
                    UUID orderId = UUID.fromString(scanner.next());
                    Order repeatedOrder = orderService.repeatOrder(orderId);
                    if (repeatedOrder != null) {
                        UUID newOrderId = orderService.placeOrder(repeatedOrder.getProducts());
                        System.out.println("Order repeated! Your new order ID: " + newOrderId);
                    } else {
                        System.out.println("Order not found.");
                    }
                }
                case 8 -> {
                    System.out.print("Enter product name to rate: ");
                    String productName = scanner.next();
                    Optional<Product> product = productService.getProducts().stream()
                            .filter(p -> p.getName().equalsIgnoreCase(productName))
                            .findFirst();
                    if (product.isPresent()) {
                        System.out.print("Enter rating (1-5): ");
                        int rating = scanner.nextInt();
                        if (rating >= MIN_RATING && rating <= MAX_RATING) {
                            product.get().addRating(rating);
                            System.out.println("Thank you for your rating!");
                        } else {
                            System.out.println("Invalid rating. Must be between " + MIN_RATING +
                                    " and " + MAX_RATING);
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                }
                case 9 -> {
                    System.out.println("Recommended Products:");
                    productService.recommendProducts().forEach(System.out::println);
                }
                case 10 -> {
                    System.out.println("Thank you for shopping with us!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
