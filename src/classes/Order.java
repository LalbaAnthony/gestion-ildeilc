package classes;

import java.time.LocalDate;

public class Order {
    private int id;
    private int customerId;
    private String name;
    private String description;
    private double total;
    private String status;
    private LocalDate creationDate;
    private LocalDate deliveryDate;

    public Order(int id, int customerId, String name, String description, double total, String status,
            LocalDate deliveryDate) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.description = description;
        this.total = total;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.creationDate = LocalDate.now();
    }

    // ================ Getters ================

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    // ================ Setters ================

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    // ================ Methods ================

    public void printOrder() {
        System.out.println("===================================");
        System.out.println("Order ID: " + this.id);
        System.out.println("Customer ID: " + this.customerId);
        System.out.println("Name: " + this.name);
        System.out.println("Description: " + this.description);
        System.out.println("Total: " + this.total);
        System.out.println("Status: " + this.status);
        System.out.println("Creation Date: " + this.creationDate);
        System.out.println("Delivery Date: " + this.deliveryDate);
        System.out.println("===================================");
    }
}
