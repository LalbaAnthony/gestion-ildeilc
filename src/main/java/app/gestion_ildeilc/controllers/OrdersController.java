package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.models.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import app.gestion_ildeilc.controllers.InvoicesController;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class OrdersController {

    // Orders sample data
    public static ObservableList<Order> orders = FXCollections.observableArrayList();

    static {
        // Sample order 1
        Order order1 = new Order("1", new Customer("1", "John", "Doe", "test@gmail.com", "123 Main St"), "Order 1", 50.0, LocalDate.of(2024, 5, 30),"Completed", Arrays.asList(
                new Line(1, new Product("1", "Product 1", "Description 1", 10.0)),
                new Line(2, new Product("2", "Product 2", "Description 2", 20.0)),
                new Line(3, new Product("3", "Product 3", "Description 3", 30.0))
        ));

        // Sample order 2
        Order order2 = new Order("2", new Customer("2", "Jane", "Smith", "jane.smith@gmail.com", "456 Elm St"), "Order 2", 70.0, LocalDate.of(2024, 6, 1),"Processing", Arrays.asList(
                new Line(1, new Product("4", "Product 4", "Description 4", 40.0)),
                new Line(2, new Product("5", "Product 5", "Description 5", 15.0)),
                new Line(3, new Product("6", "Product 6", "Description 6", 15.0))
        ));

        // Adding orders to the list
        orders.addAll(order1, order2);
    }

    // Get all orders
    public static ObservableList<Order> getAllOrders() {
        return orders;
    }

    // Get order by id
    public static Order getOrderById(String id) {
        for (Order order : orders) {
            if (Objects.equals(order.getId(), id)) {
                return order;
            }
        }
        return null;
    }

    // Delete order
    public static boolean deleteOrder(Order order) {
        return orders.remove(order);
    }

    // Generate a unique ID
    public static String generateId() {
        int maxId = 0;
        for (Order order : orders) {
            int id = Integer.parseInt(order.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.valueOf(maxId + 1);
    }


    // Add a new order
    public static void addOrder(Order newOrder) {
        orders.add(newOrder);
    }

    private void generateInvoice(Order order) {
        if (!order.canCreateInvoice()) {
            // Optionally show a message to the user that the order cannot be completed
            return;
        }

        order.setStatus("Completed");

        Invoice invoice = new Invoice(
                order.getId(),
                order.getCustomer(),
                order.getDescription(),
                order.getTotal(),
                order.getDeliveryDate(),
                "Processing",
                new ArrayList<>(order.getLines())
        );

        InvoicesController.invoices.add(invoice);

        // Optionally update the UI to reflect the change in order status, no utility for now but might be useful in da future
        // ordersTable.refresh();
    }
}