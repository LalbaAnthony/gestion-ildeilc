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

    private BorderPane root; // Garder comme champ pour accéder dans différentes méthodes

    public static int height = 480;
    public static int width = 640;

    @Override
    public void start(Stage stage) throws IOException {

        // Créez des boutons que vous souhaitez placer dans l'en-tête
        Button button1 = new Button("Button 1");

        // Ajout d'action pour changer la vue en utilisant les boutons
        button1.setOnAction(e -> switchView("hello-view.fxml"));

        // Créez un conteneur VBox pour les boutons de l'en-tête
        VBox topMenu = new VBox();
        topMenu.getChildren().addAll(button1);

        // Créez un conteneur BorderPane pour l'en-tête
        BorderPane header = new BorderPane();
        header.setTop(topMenu);

        // Initialisation du BorderPane racine
        root = new BorderPane();
        root.setTop(header); // Fixer l'en-tête

        // Charger la vue initiale
        switchView("home-view.fxml");

        // Créez une scène avec le conteneur BorderPane principal
        Scene scene = new Scene(root, width, height);

        // Définissez la scène et affichez la fenêtre
        stage.setTitle("Gestion ILDEILC");
        stage.setScene(scene);
        stage.show();
    }

    // Méthode pour changer la vue dans la partie centrale de BorderPane
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
