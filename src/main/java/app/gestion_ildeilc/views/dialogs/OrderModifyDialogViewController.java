package app.gestion_ildeilc.views.dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import app.gestion_ildeilc.controllers.OrderController;
import app.gestion_ildeilc.controllers.CustomerController;
import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Customer;
import javafx.scene.control.TableView;
import app.gestion_ildeilc.models.Line;
import javafx.collections.FXCollections;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

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

    private Stage dialogStage;
    private Order order;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        orderStatusComboBox.getItems().addAll("Pending", "Processing", "Completed", "Cancelled");

        // Initialize the Spinner for totalField
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 0.1);
        totalSpinner.setValueFactory(valueFactory);

        // Configuration du StringConverter pour afficher les noms des clients
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

        // Populate the customer select
        customerComboBox.setItems(FXCollections.observableArrayList(CustomerController.customers));

        // Set autoresize property
        linesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrder(Order order) {
        this.order = order;

        idField.setText(String.valueOf(order.getId()));
        descriptionField.setText(order.getDescription());
        orderStatusComboBox.setValue(order.getStatus());
        customerComboBox.setValue(order.getCustomer());
        deliveryDatePicker.setValue(order.getDeliveryDate());
        totalSpinner.getValueFactory().setValue(order.getTotal());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            // order.setId(idField.getText()); // No set ID cuz it cannot be changed
            order.setDescription(descriptionField.getText());
            order.setStatus(orderStatusComboBox.getValue());
            order.setCustomer(customerComboBox.getValue());
            order.setDeliveryDate(deliveryDatePicker.getValue());
            order.setTotal(totalSpinner.getValue());

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
