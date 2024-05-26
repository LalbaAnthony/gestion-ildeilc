package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import app.gestion_ildeilc.models.Invoice;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.models.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class InvoicesController {

    // Invoices sample data
    public static ObservableList<Invoice> invoices = FXCollections.observableArrayList();

    static {
        // Sample invoice 1
        Invoice invoice1 = new Invoice("1", new Customer("1", "John", "Doe", "test@gmail.com", "123 Main St"), "Invoice 1", 50.0, LocalDate.of(2024, 6, 5), true, Arrays.asList(
                new Line(1, new Product("1", "Product 1", "Description 1", 10.0)),
                new Line(2, new Product("2", "Product 2", "Description 2", 20.0)),
                new Line(3, new Product("3", "Product 3", "Description 3", 30.0))
        ));

        // Adding invoices to the list
        invoices.addAll(invoice1);
    }

    // Get all invoices
    public static ObservableList<Invoice> getAllInvoices() {
        return invoices;
    }

    // Get invoice by id
    public static Invoice getInvoiceById(String id) {
        for (Invoice invoice : invoices) {
            if (Objects.equals(invoice.getId(), id)) {
                return invoice;
            }
        }
        return null;
    }

    // Delete invoice
    public static boolean deleteInvoice(Invoice invoice) {
        return invoices.remove(invoice);
    }

    // Generate a unique ID
    public static String generateId() {
        int maxId = 0;
        for (Invoice invoice : invoices) {
            int id = Integer.parseInt(invoice.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.valueOf(maxId + 1);
    }


    // Add a new invoice
    public static void addInvoice(Invoice newInvoice) {
        invoices.add(newInvoice);
    }
}