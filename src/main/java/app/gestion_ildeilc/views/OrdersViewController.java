package app.gestion_ildeilc.views;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import app.gestion_ildeilc.items.Customer;
import app.gestion_ildeilc.items.Order;

// Importing utils
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrdersViewController {

    // Orders sample data
    public static Order[] orders = {
            new Order(1, new Customer(1, "John", "Doe", "tets@gmail.com", "Lorem address"), "Order 1", 100.1, LocalDate.of(2021, 10, 1)),
            new Order(2, new Customer(1, "John", "Doe", "tets@gmail.com", "Lorem address"), "Order 2", 200.0, LocalDate.of(2021, 10, 2))
    };

    @FXML
    private TableView<Order> ordersTable;

    public void initialize() {
        // ID column
        TableColumn<Order, String> orderIdCol = new TableColumn<>("Order ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Description column
        TableColumn<Order, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Total column
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

        // Delivery date column
        TableColumn<Order, LocalDate> deliveryDateCol = new TableColumn<>("Delivery date");
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

        // Date format for the delivery date column
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

        // Customer name column
        TableColumn<Order, String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(cellData -> {
            Customer customer = cellData.getValue().getCustomer();
            return new SimpleStringProperty(customer.getNiceName());
        });

        // Delete button column
        TableColumn<Order, Void> deleteCol = new TableColumn<>(" ");
        deleteCol.setCellFactory(param -> new TableCell<Order, Void>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(order);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(deleteButton);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        // Add all columns to the table
        ordersTable.getColumns().addAll(orderIdCol, descriptionCol, totalCol, deliveryDateCol, customerNameCol, deleteCol);

        // Populate table with sample data
        ordersTable.getItems().addAll(orders);
    }
}
