module org.example.gestion_ildeilc {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.gestion_ildeilc to javafx.fxml;
    exports org.example.gestion_ildeilc;
}