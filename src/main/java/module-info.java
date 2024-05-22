module app.gestion_ildeilc {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.gestion_ildeilc to javafx.fxml;
    exports app.gestion_ildeilc;

    opens app.gestion_ildeilc.models to javafx.base;

    opens app.gestion_ildeilc.views to javafx.fxml;
    exports app.gestion_ildeilc.views;
}