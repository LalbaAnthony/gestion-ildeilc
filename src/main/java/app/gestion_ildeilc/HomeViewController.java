package app.gestion_ildeilc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeViewController {

    @FXML
    Button ordersBtn;

    public void goToOrders() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Stage window = (Stage) ordersBtn.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

}