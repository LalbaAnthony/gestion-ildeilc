package app.gestion_ildeilc.models;

import app.gestion_ildeilc.controllers.DeliveryNotesController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order extends Transaction {

    private String status;
    private LocalDate deliveryDate;

    public Order(String id, Customer customer, String description, double total, LocalDate deliveryDate, String status, List<Line> lines) {
        super(id, customer, description, total, lines);
        this.status = Objects.requireNonNullElse(status, "Pending"); // Set status to 'pending' if no value
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
        // Create delivery note
        if (("Received".equals(status) || "Sent".equals(status))) {
            DeliveryNotesController.addDeliveryNote(new DeliveryNote(DeliveryNotesController.generateId(), getId(), LocalDate.now()));
        }

        // Check for stock
        double calculatedTotal = 0.0;
        for (Line line : getLines()) {
            if (line.getProduct().getStock() == 0) {
                this.status = "Pending";
                break;
            }
        }
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    // ================ Computed ================

    public boolean canModify() {
        return !("Cancelled".equals(status) || "Received".equals(status) || "Sent".equals(status));
    }

    public boolean canCreateInvoice() {
        return ("Received".equals(status) || "Sent".equals(status));
    }
}
