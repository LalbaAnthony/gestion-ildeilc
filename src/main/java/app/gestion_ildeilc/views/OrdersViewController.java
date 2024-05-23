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
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Order;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import app.gestion_ildeilc.controllers.OrderController;
import java.net.URL;

public class OrdersViewController {

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

        // Status column
        TableColumn<Order, LocalDate> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Modify button column
        TableColumn<Order, Void> modifyCol = new TableColumn<>(" ");
        modifyCol.setCellFactory(param -> new TableCell<Order, Void>() {
            private final Button modifyButton = new Button("Modify");
            {
                modifyButton.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
                modifyButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    showModifyOrderDialog(order);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(modifyButton);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        // Delete button column
        TableColumn<Order, Void> deleteCol = new TableColumn<>(" ");
        deleteCol.setCellFactory(param -> new TableCell<Order, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(order);
                    OrderController.deleteOrder(order);
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
        ordersTable.getColumns().addAll(orderIdCol, descriptionCol, totalCol, deliveryDateCol, customerNameCol, statusCol, modifyCol, deleteCol);

        // Set autoresize property
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Populate table with sample data
        ordersTable.setItems(FXCollections.observableArrayList(OrderController.orders));
    }

    private void showModifyOrderDialog(Order order) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/app/gestion_ildeilc/order-modify-dialog.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                return;
            }
            loader.setLocation(fxmlLocation);
            Parent page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Order");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ordersTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            OrderModifyDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrder(order);

            dialogStage.showAndWait();

            if (controller.isSaveClicked()) {
                ordersTable.refresh();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

