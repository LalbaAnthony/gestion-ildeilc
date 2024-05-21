package app.gestion_ildeilc.views;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import app.gestion_ildeilc.items.Customer;
import app.gestion_ildeilc.items.Order;

// Importing utils
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrdersViewController {

    @FXML
    private TableView<Order> ordersTable;

    public void initialize() {
        // Initialize columns with properties of the Order class
        TableColumn<Order, String> orderIdCol = new TableColumn<>("Order ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Order, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Order, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totalCol.setCellFactory(column -> new TableCell<Order, Double>() {
            private final StringConverter<Number> converter = new NumberStringConverter();

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(converter.toString(item) + " €");
                }
            }
        });

        TableColumn<Order, LocalDate> deliveryDateCol = new TableColumn<>("Delivery date");
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

        // Set custom cell factory for date formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        deliveryDateCol.setCellFactory(column -> new TextFieldTableCell<>(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? date.format(formatter) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return string != null && !string.isEmpty() ? LocalDate.parse(string, formatter) : null;
            }
        }));

        // Add columns to the table
        ordersTable.getColumns().addAll(orderIdCol, descriptionCol, totalCol, deliveryDateCol);

        // Add customer name column
        TableColumn<Order, String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(cellData -> {
            Customer customer = cellData.getValue().getCustomer();
            return new javafx.beans.property.SimpleStringProperty(customer.getNiceName());
        });

        ordersTable.getColumns().add(customerNameCol);

        // Populate table with sample data
        Customer customer1 = new Customer(1, "John", "Doe", "tets@gmail.com", "Lorem adress");
        ordersTable.getItems().addAll(
                new Order(1, customer1, "Order 1", 100.1, LocalDate.of(2021, 10, 1)),
                new Order(2, customer1, "Order 2", 200.0, LocalDate.of(2021, 10, 2))
        );
    }
}
