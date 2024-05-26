package app.gestion_ildeilc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    private BorderPane root;

    // Properties: height and width of the screen
    public static int width = 640;
    public static int height = 480;

    @Override
    public void start(Stage stage) throws IOException {

        // Create the header buttons
        Button btnOrders = new Button("Orders");
        Button btnDeliveryNotes = new Button("Delivery notes");
        Button btnInvoices = new Button("Invoices");
        Button btnClose = new Button("Close app"); // Button to close the app

        // Add action to change the view using the buttons
        btnOrders.setOnAction(e -> switchView("orders-page-view.fxml"));
        btnDeliveryNotes.setOnAction(e -> switchView("delivery-notes-view.fxml"));
        btnInvoices.setOnAction(e -> switchView("invoices-view.fxml"));
        btnClose.setOnAction(e -> stage.close()); // Action to close the app when clicked

        // Apply CSS styling to the buttons
        btnOrders.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
        btnDeliveryNotes.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
        btnInvoices.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
        btnClose.setStyle("-fx-background-color: #848484; -fx-text-fill: white; -fx-font-weight: bold;"); // Styling for close button

        // Create a HBox container for the header buttons
        HBox topMenu = new HBox(10); // Set a 10 spacing between buttons | HBox -> Horizontal flex, VBox Vertical flex
        topMenu.getChildren().addAll(btnOrders, btnDeliveryNotes, btnInvoices, btnClose);
        topMenu.setAlignment(javafx.geometry.Pos.CENTER); // Align buttons in center

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

        // Load and set the icon, the image is meant to be replaced in the future or in a real use case
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/logo_gestion_ILDEILC_transp.png")));
        stage.getIcons().add(icon);

        // Set and show scene
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
