package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Product;

import java.util.Objects;

public class ProductsController {

    // Products sample data
    public static Product[] products = {
            new Product("1", "Product 1", "Lorem ipsum", 10.0),
            new Product("2", "Product 2", "Lorem ipsum", 20.0),
            new Product("3", "Product 3", "Lorem ipsum", 30.0),
    };

    // Get all products
    public static Product[] getAllProducts() {
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
        for (int i = 0; i < products.length; i++) {
            if (Objects.equals(products[i].getId(), product.getId())) {
                Product[] newProducts = new Product[products.length - 1];
                System.arraycopy(products, 0, newProducts, 0, i);
                System.arraycopy(products, i + 1, newProducts, i, products.length - i - 1);
                products = newProducts;
                return true;
            }
        }
        return false;
    }

}