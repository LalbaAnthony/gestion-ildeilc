module app.gestion_ildeilc {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.gestion_ildeilc to javafx.fxml;
    exports app.gestion_ildeilc;
}