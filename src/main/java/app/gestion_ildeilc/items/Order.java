package app.gestion_ildeilc.items;

import java.time.LocalDate;

public class Order extends Transaction {

    private String status;
    private LocalDate deliveryDate;

    public Order(int id, int customerId, String description, double total, LocalDate deliveryDate) {
        super(id, customerId, description, total);
        this.status = "Pending";
        this.deliveryDate = deliveryDate;
    }

    // ================ Getters ================

    public String getStatus() {
        return status;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    // ================ Setters ================

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    // ================ Methods ================

    public void printOrder() {
        System.out.println("===================================");
        System.out.println("Order ID: " + getId());
        System.out.println("Customer ID: " + getCustomerId());
        System.out.println("Description: " + getDescription());
        System.out.println("Total: " + getTotal());
        System.out.println("Status: " + status);
        System.out.println("Creation Date: " + getCreationDate());
        System.out.println("Delivery Date: " + deliveryDate);
        System.out.println("===================================");
    }
}
