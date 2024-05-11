module app.gestion_ildeilc {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.gestion_ildeilc to javafx.fxml;
    opens app.gestion_ildeilc.items to javafx.base;

    exports app.gestion_ildeilc;
}