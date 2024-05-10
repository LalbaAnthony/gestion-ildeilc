package app.gestion_ildeilc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static int height = 480;
    public static int width = 640;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Gestion ILDEILC");
        stage.setScene(scene);
        stage.show();
    }

    public void goToOrders() {
        System.out.println("The color you have chosen is not in the list");
    }

    public static void main(String[] args) {
        launch();
    }
}