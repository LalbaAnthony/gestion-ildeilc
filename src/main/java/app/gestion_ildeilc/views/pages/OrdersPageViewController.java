package app.gestion_ildeilc.views.pages;

import app.gestion_ildeilc.controllers.OrdersController;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.views.dialogs.OrderDialogViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class OrdersPageViewController {

    @FXML
    public TableView<Order> ordersTable;

    public void initialize() {

        // ID column
        TableColumn<Order, String> orderIdCol = new TableColumn<>("ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Description column
        TableColumn<Order, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Status column
        TableColumn<Order, LocalDate> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Customer name column
        TableColumn<Order, String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(cellData -> {
            Customer customer = cellData.getValue().getCustomer();
            return new SimpleStringProperty(customer.getNiceName());
        });

        // Delivery date column
        TableColumn<Order, LocalDate> deliveryDateCol = new TableColumn<>("Delivery date");
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
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

        // Invoice button column
        TableColumn<Order, Void> createInvoiceCol = new TableColumn<>(" ");
        createInvoiceCol.setCellFactory(param -> new TableCell<Order, Void>() {
            private final Button modifyButton = new Button("Create an invoice");

            {
                modifyButton.setStyle("-fx-background-color: #FF7F50; -fx-text-fill: white; -fx-font-weight: bold;");
                modifyButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    OrdersController.createInvoice(order);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Order order = getTableView().getItems().get(getIndex());
                    // Disable the modification if depending on the status
                    modifyButton.setDisable(!order.canCreateInvoice());
                    HBox hBox = new HBox(modifyButton);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        // Modify button column
        TableColumn<Order, Void> modifyCol = new TableColumn<>(" ");
        modifyCol.setCellFactory(param -> new TableCell<Order, Void>() {
            private final Button modifyButton = new Button("Modify");

            {
                modifyButton.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
                modifyButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    showOrderDialogModify(order);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Order order = getTableView().getItems().get(getIndex());
                    // Disable the modification if depending on the status
                    modifyButton.setDisable(!order.canModify());
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
                    OrdersController.deleteOrder(order);
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
        ordersTable.getColumns().addAll(orderIdCol, descriptionCol, statusCol, customerNameCol, deliveryDateCol, totalCol, createInvoiceCol, modifyCol, deleteCol);

        // Set autoresize property
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Populate table with sample data
        ordersTable.setItems(OrdersController.orders);
    }

    @FXML
    private void showOrderDialogCreation() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/app/gestion_ildeilc/order-dialog-view.fxml");
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
            Scene scene = new Scene(page, 480, 640);
            dialogStage.setScene(scene);

            OrderDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // If is an order creation
            controller.isModification = false;
            controller.pageTitle.setText("Create an order");
            Order order = new Order(OrdersController.generateId(), null, null, 0.0, null, "Pending", Arrays.asList(new Line(2, new Product("1", "Product 1", "Lorem ipsum", 21.5, 10))));
            controller.setOrder(order);

            dialogStage.showAndWait();

            if (controller.isSaveClicked()) {
                ordersTable.refresh();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showOrderDialogModify(Order order) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/app/gestion_ildeilc/order-dialog-view.fxml");
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
            Scene scene = new Scene(page, 480, 640);
            dialogStage.setScene(scene);

            OrderDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // If is an order modification
            controller.isModification = true;
            controller.pageTitle.setText("Modify an order");
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

