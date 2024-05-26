package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class ProductsController {

    // Products sample data
    public static ObservableList<Product> products = FXCollections.observableArrayList();

    static {
        Product product1 = new Product("1", "Product 1", "Description 1", 10.0);
        Product product2 = new Product("2", "Product 2", "Description 2", 20.0);
        Product product3 = new Product("3", "Product 3", "Description 3", 30.0);
        Product product4 = new Product("4", "Product 4", "Description 4", 40.0);
        Product product5 = new Product("5", "Product 5", "Description 5", 15.0);
        Product product6 = new Product("6", "Product 6", "Description 6", 15.0);

        // Adding products to the list
        products.addAll(product1, product2, product3, product4, product5, product6);
    }

    // Get all products
    public static ObservableList<Product> getAllProducts() {
        return products;
    }

    // Get product by id
    public static Product getProductById(String id) {
        for (Product product : products) {
            if (Objects.equals(product.getId(), id)) {
                return product;
            }
        }
        return null;
    }

    // Delete product
    public static boolean deleteProduct(Product product) {
        return products.remove(product);
    }

    // Generate a unique ID
    public static String generateId() {
        int maxId = 0;
        for (Product product : products) {
            int id = Integer.parseInt(product.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.valueOf(maxId + 1);
    }


    // Add a new product
    public static void addProduct(Product newProduct) {
        products.add(newProduct);
    }
}