package app.gestion_ildeilc.views.dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import app.gestion_ildeilc.models.Order;
import javafx.collections.FXCollections;
import app.gestion_ildeilc.models.Customer;
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
    private TextField totalField;

    private Stage dialogStage;
    private Order order;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        orderStatusComboBox.getItems().addAll("Pending", "Processing", "Completed", "Cancelled");

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

        customerComboBox.setItems(FXCollections.observableArrayList(
                new Customer(1, "John", "Doe", "tets@gmail.com", "Lorem address"),
                new Customer(1, "John 2", "Doe", "tets@gmail.com", "Lorem address")
        ));
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
        totalField.setText(String.valueOf(order.getTotal()));
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
            order.setTotal(Double.parseDouble(totalField.getText()));

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
