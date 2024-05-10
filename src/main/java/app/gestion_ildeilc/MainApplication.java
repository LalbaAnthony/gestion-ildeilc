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

    public static int height = 480;
    public static int width = 640;

    @Override
    public void start(Stage stage) throws IOException {

        // Créez des boutons que vous souhaitez placer dans l'en-tête
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");

        // Créez un conteneur VBox pour les boutons de l'en-tête
        VBox topMenu = new VBox();
        topMenu.getChildren().addAll(button1, button2, button3);

        // Créez un conteneur BorderPane pour l'en-tête
        BorderPane header = new BorderPane();
        header.setTop(topMenu); // Placez le VBox en haut de l'en-tête

        // Chargez le fichier FXML principal pour le contenu principal de l'application
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-view.fxml"));
        Parent content = fxmlLoader.load();

        BorderPane root = new BorderPane();
        root.setTop(header); // Placez l'en-tête en haut de la page principale
        root.setCenter(content); // Placez le contenu principal au centre de la page principale

        // Créez une scène avec le conteneur BorderPane principal
        Scene scene = new Scene(root, width, height);

        // Définissez la scène et affichez la fenêtre
        stage.setTitle("Gestion ILDEILC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}