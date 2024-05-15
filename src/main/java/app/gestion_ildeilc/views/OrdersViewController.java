package app.gestion_ildeilc.views;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import app.gestion_ildeilc.items.Order;

public class OrdersViewController {

    @FXML
    private TableView<Order> ordersTable;

    public void initialize() {
        // Initialize columns with properties of the Order class
        TableColumn<Order, String> orderIdCol = new TableColumn<>("Order ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<Order, String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        // Add columns to the table
        ordersTable.getColumns().addAll(orderIdCol, customerNameCol);

        // Populate table with sample data (replace with the actual data)
        ordersTable.getItems().addAll(
                new Order("1", "John Doe"),
                new Order("2", "Jane Smith")
        );
    }
}
