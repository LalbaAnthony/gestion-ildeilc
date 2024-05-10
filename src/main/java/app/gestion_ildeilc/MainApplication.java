package app.gestion_ildeilc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import java.io.IOException;

public class MainApplication extends Application {

    private BorderPane root; // Keep as a field for access in different methods

    public static int height = 480;
    public static int width = 640;

    @Override
    public void start(Stage stage) throws IOException {

        // Create buttons that you want to place in the header
        Button btnOrders = new Button("Orders");
        Button btnDeliveryNotes = new Button("Delivery notes");
        Button btnInvoices = new Button("Invoices");

        // Add action to change the view using the button
        btnOrders.setOnAction(e -> switchView("hello-view.fxml"));
        btnDeliveryNotes.setOnAction(e -> switchView("hello-view.fxml"));
        btnInvoices.setOnAction(e -> switchView("hello-view.fxml"));

        // Create a VBox container for the header buttons
        VBox topMenu = new VBox();
        topMenu.getChildren().addAll(btnOrders, btnDeliveryNotes, btnInvoices);

        // Create a BorderPane container for the header
        BorderPane header = new BorderPane();
        header.setTop(topMenu);

        // Initialize the main BorderPane
        root = new BorderPane();
        root.setTop(header); // Set the header

        // Load the initial view
        switchView("home-view.fxml");

        // Create a scene with the main BorderPane container
        Scene scene = new Scene(root, width, height);

        // Set the scene and display the window
        stage.setTitle("Gestion ILDEILC");
        stage.setScene(scene);
        stage.show();
    }

    // Method to change the view in the central part of BorderPane
    private void switchView(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent view = fxmlLoader.load();
            root.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
