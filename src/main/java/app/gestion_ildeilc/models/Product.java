package app.gestion_ildeilc.models;

public class Product {
    private final String id;
    private String name;

    private String description;

    private double price;

    private int stock;

    public Product(String id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // ================ Getters ================

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // ================ Setters ================


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = Product.this.description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
