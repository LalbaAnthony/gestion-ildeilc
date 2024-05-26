package app.gestion_ildeilc.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Invoice extends Transaction {

    private String paid;
    private LocalDate deliveredDate;

    public Invoice(String id, Customer customer, String description, double total, LocalDate deliveredDate, String paid, List<Line> lines) {
        super(id, customer, description, total, lines);
        this.paid = Objects.requireNonNullElse(paid, "Pending"); // Set status to 'pending' if no value
        this.deliveredDate = deliveredDate;
    }

    // ================ Getters ================

    public String getPaid() {
        return paid;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    // ================ Setters ================

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    // ================ Computed ================

    public boolean canModify() {
        return !("Paid".equals(paid));
    }
}
