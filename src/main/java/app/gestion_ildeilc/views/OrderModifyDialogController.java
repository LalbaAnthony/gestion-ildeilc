package app.gestion_ildeilc.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import app.gestion_ildeilc.models.Order;

import java.time.LocalDate;

public class OrderModifyDialogController {

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField totalField;

    @FXML
    private DatePicker deliveryDatePicker;

    private Stage dialogStage;
    private Order order;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrder(Order order) {
        this.order = order;

        descriptionField.setText(order.getDescription());
        totalField.setText(String.valueOf(order.getTotal()));
        deliveryDatePicker.setValue(order.getDeliveryDate());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            order.setDescription(descriptionField.getText());
            order.setTotal(Double.parseDouble(totalField.getText()));
            order.setDeliveryDate(deliveryDatePicker.getValue());

            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        // Add validation logic if needed
        return true;
    }
}
