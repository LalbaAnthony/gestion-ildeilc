package app.gestion_ildeilc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void goToOrder() {
        welcomeText.setText("");
    }

    @FXML
    protected void goToDeliveryNotes() {
        welcomeText.setText("");
    }

    @FXML
    protected void goToInvoice() {
        welcomeText.setText("");
    }
}