module app.gestion_ildeilc {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.gestion_ildeilc to javafx.fxml;
    exports app.gestion_ildeilc;

    opens app.gestion_ildeilc.models to javafx.base;

    // Pages
    exports app.gestion_ildeilc.views.pages;
    opens app.gestion_ildeilc.views.pages to javafx.fxml;

    // Dialogs
    exports app.gestion_ildeilc.views.dialogs;
    opens app.gestion_ildeilc.views.dialogs to javafx.fxml;
}