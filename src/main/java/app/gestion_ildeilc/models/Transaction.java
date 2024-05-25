package app.gestion_ildeilc.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final String id;
    private String description;
    private double total;
    private Customer customer;
    private final LocalDate creationDate;
    private List<Line> lines;

    public Transaction(String id, Customer customer, String description, double total, List<Line> lines) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.total = total;
        this.creationDate = LocalDate.now();
        this.lines = new ArrayList<>(lines); // To make the collection editable
        calculateTotal(); // Initial calculation of the total, to ensure data integrity
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

    public List<Line> getLines() {
        return lines;
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

    public void setLines(List<Line> lines) {
        this.lines = new ArrayList<>(lines); // To make the collection editable
        calculateTotal(); // Recalculate the total when lines are set
    }

    // ================ Methods ================

    public void calculateTotal() {
        double calculatedTotal = 0.0;
        for (Line line : lines) {
            calculatedTotal += line.getQuantity() * line.getProduct().getPrice();
        }
        this.total = calculatedTotal;
    }

    public void addLine(Line line) {
        this.lines.add(line);
        calculateTotal();
    }

    public void deleteLine(Line line) {
        this.lines.remove(line);
        calculateTotal();
    }
}
