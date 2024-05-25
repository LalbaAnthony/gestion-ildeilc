package app.gestion_ildeilc.models;

import java.time.LocalDate;
import java.util.List;

public class Order extends Transaction {

    private String status;
    private LocalDate deliveryDate;

    public Order(String id, Customer customer, String description, double total, LocalDate deliveryDate, List<Line> lines) {
        super(id, customer, description, total, lines);
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
}
