package app.gestion_ildeilc.views;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import app.gestion_ildeilc.items.Order;

// Importing utils
import java.time.LocalDate;

public class OrdersViewController {

    @FXML
    private TableView<Order> ordersTable;

    public void initialize() {
        // Initialize columns with properties of the Order class
        TableColumn<Order, String> orderIdCol = new TableColumn<>("Order ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Order, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Order, String> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        // Add columns to the table
        ordersTable.getColumns().addAll(orderIdCol, descriptionCol, totalCol);

        // Populate table with sample data (replace with the actual data)
        ordersTable.getItems().addAll(
                new Order(1, 1, "Order 1", 100.0, LocalDate.of(2021, 10, 1)),
                new Order(2, 2, "Order 2", 200.0, LocalDate.of(2021, 10, 2))
        );
    }
}
