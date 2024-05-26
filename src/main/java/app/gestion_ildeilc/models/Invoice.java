package app.gestion_ildeilc.models;

import java.time.LocalDate;
import java.util.List;

public class Invoice extends Transaction {

    private boolean paid;
    private LocalDate deliveredDate;

    public Invoice(String id, Customer customer, String description, double total, LocalDate deliveredDate, boolean paid, List<Line> lines) {
        super(id, customer, description, total, lines);
        this.paid = paid;
        this.deliveredDate = deliveredDate;
    }

    // ================ Getters ================

    public boolean getStatus() {
        return paid;
    }

    public LocalDate getDeliveryDate() {
        return deliveredDate;
    }

    // ================ Setters ================

    public void setStatus(boolean paid) {
        this.paid = paid;
    }

    public void setDeliveryDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
}
