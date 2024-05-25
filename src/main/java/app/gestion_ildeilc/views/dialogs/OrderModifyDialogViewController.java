package app.gestion_ildeilc.views.dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.collections.FXCollections;
import app.gestion_ildeilc.controllers.CustomersController;
import app.gestion_ildeilc.controllers.ProductsController;
import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import javafx.geometry.Pos;

public class OrderModifyDialogViewController {

    @FXML
    private TextField idField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<String> orderStatusComboBox;

    @FXML
    private DatePicker deliveryDatePicker;

    @FXML
    private Spinner<Double> totalSpinner;

    @FXML
    private TableView<Line> linesTable;

    @FXML
    private ComboBox<Product> productComboBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    private Stage dialogStage;
    private Order order;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        // Set values status select
        orderStatusComboBox.getItems().addAll("Pending", "Processing", "Completed", "Cancelled");

        // Initialize the Spinner for totalField
        SpinnerValueFactory<Double> totalValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 0.1);
        totalSpinner.setValueFactory(totalValueFactory);

        // StringConverter configuration to display client's names
        customerComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Customer customer) {
                return customer != null ? customer.getNiceName() : "";
            }

            @Override
            public Customer fromString(String string) {
                return customerComboBox.getItems().stream()
                        .filter(customer -> customer.getNiceName().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
        customerComboBox.setItems(FXCollections.observableArrayList(CustomersController.customers)); // Populate the customer select

        // StringConverter configuration to display client's names
        productComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Product product) {
                return product != null ? product.getName() : "";
            }

            @Override
            public Product fromString(String string) {
                return productComboBox.getItems().stream()
                        .filter(product -> product.getName().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
        productComboBox.setItems(FXCollections.observableArrayList(ProductsController.products)); // Populate the product select

        SpinnerValueFactory<Integer> quantityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1, 1);
        quantitySpinner.setValueFactory(quantityValueFactory);

        // Product column
        TableColumn<Line, String> linesProductCol = new TableColumn<>("Product");
        linesProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        linesProductCol.setCellValueFactory(cellData -> {
            Product product = cellData.getValue().getProduct();
            return new SimpleStringProperty(product.getName());
        });

        // Quantity column
        TableColumn<Line, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Delete button column
        TableColumn<Line, Void> deleteCol = new TableColumn<>(" ");
        deleteCol.setCellFactory(param -> new TableCell<Line, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    Line line = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(line);
                    order.deleteLine(line); // Remove the line from the order
                    order.calculateTotal(); // Recalculate the total
                    totalSpinner.getValueFactory().setValue(order.getTotal()); // Update the spinner value
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
        linesTable.getColumns().addAll(linesProductCol, quantityCol, deleteCol);

        // Set autoresize property
        linesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrder(Order order) {
        this.order = order;

        // Set all field with the order data
        idField.setText(String.valueOf(order.getId()));
        descriptionField.setText(order.getDescription());
        orderStatusComboBox.setValue(order.getStatus());
        customerComboBox.setValue(order.getCustomer());
        deliveryDatePicker.setValue(order.getDeliveryDate());
        totalSpinner.getValueFactory().setValue(order.getTotal());
        linesTable.setItems(FXCollections.observableArrayList(order.getLines()));
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleAddLine() {

        // Get values, set the qty to 1 just in case
        Product product = productComboBox.getValue();
        Integer quantity = quantitySpinner.getValue();
        if (quantity == null) {
            quantity = 1;
        }

        Line newLine = new Line(quantity, product); // Create Line object

        linesTable.getItems().add(newLine); // add the line to the table
        order.addLine(newLine); // Add line to the actual order

        // Clear the input fields
        productComboBox.getSelectionModel().clearSelection();
        quantitySpinner.getValueFactory().setValue(null);
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            // Set of all values, might be replaced in the future by a dedicated methode
            // order.setId(idField.getText()); // No set cuz it cannot be changed by the user
            order.setDescription(descriptionField.getText());
            order.setStatus(orderStatusComboBox.getValue());
            order.setCustomer(customerComboBox.getValue());
            order.setDeliveryDate(deliveryDatePicker.getValue());
            // order.setTotal(totalSpinner.getValue()); // No set cuz it cannot be changed by the user
            order.setLines(linesTable.getItems());

            order.calculateTotal();  // Calculate the total before saving

            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        return true;
    }
}
