package app.gestion_ildeilc.models;

import java.time.LocalDate;

public class Transaction {
    private final String id;
    private String description;
    private double total;
    private Customer customer;
    private final LocalDate creationDate;

    public Transaction(String id, Customer customer, String description, double total) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.total = total;
        this.creationDate = LocalDate.now();
    }

    // ================ Getters ================

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDescription() {
        return description;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    // ================ Setters ================

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
