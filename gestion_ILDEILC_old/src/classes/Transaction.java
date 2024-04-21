package classes;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int customerId;
    private String description;
    private double total;
    private LocalDate creationDate;

    public Transaction(int id, int customerId, String description, double total) {
        this.id = id;
        this.customerId = customerId;
        this.description = description;
        this.total = total;
        this.creationDate = LocalDate.now();
    }

    // ================ Getters ================

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
