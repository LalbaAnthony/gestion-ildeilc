package app.gestion_ildeilc.views.pages;

import app.gestion_ildeilc.controllers.ProductsController;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.views.dialogs.ProductDialogViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ProductsPageViewController {

    @FXML
    public TableView<Product> productsTable;

    public void initialize() {

        // ID column
        TableColumn<Product, String> productIdCol = new TableColumn<>("ID");
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Name column
        TableColumn<Product, LocalDate> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Description column
        TableColumn<Product, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Price column
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellFactory(column -> new TableCell<Product, Double>() {
            private final StringConverter<Number> converter = new NumberStringConverter();

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(converter.toString(item) + " €");
                }
            }
        });

        // Stock column
        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setCellFactory(column -> new TableCell<Product, Integer>() {
            private final StringConverter<Number> converter = new NumberStringConverter();

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(converter.toString(item) + " u.");
                }
            }
        });

        // Modify button column
        TableColumn<Product, Void> modifyCol = new TableColumn<>(" ");
        modifyCol.setCellFactory(param -> new TableCell<Product, Void>() {
            private final Button modifyButton = new Button("Modify");

            {
                modifyButton.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
                modifyButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    showProductDialogModify(product);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Product product = getTableView().getItems().get(getIndex());
                    HBox hBox = new HBox(modifyButton);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        // Delete button column
        TableColumn<Product, Void> deleteCol = new TableColumn<>(" ");
        deleteCol.setCellFactory(param -> new TableCell<Product, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(product);
                    ProductsController.deleteProduct(product);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(deleteButton);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        // Add all columns to the table
        productsTable.getColumns().addAll(productIdCol, nameCol, descriptionCol, priceCol, stockCol, modifyCol, deleteCol);

        // Set autoresize property
        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Populate table with sample data
        productsTable.setItems(ProductsController.products);
    }

    @FXML
    private void showProductDialogCreation() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/app/gestion_ildeilc/product-dialog-view.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                return;
            }
            loader.setLocation(fxmlLocation);
            Parent page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(productsTable.getScene().getWindow());
            Scene scene = new Scene(page, 480, 360);
            dialogStage.setScene(scene);

            ProductDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // If is a product creation
            controller.isModification = false;
            controller.pageTitle.setText("Create a product");
            Product product = new Product(ProductsController.generateId(), null, null, 0.0, 1);
            controller.setProduct(product);

            dialogStage.showAndWait();

            if (controller.isSaveClicked()) {
                productsTable.refresh();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductDialogModify(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/app/gestion_ildeilc/product-dialog-view.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                return;
            }
            loader.setLocation(fxmlLocation);
            Parent page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(productsTable.getScene().getWindow());
            Scene scene = new Scene(page, 480, 360);
            dialogStage.setScene(scene);

            ProductDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // If is a product modification
            controller.isModification = true;
            controller.pageTitle.setText("Modify a product");
            controller.setProduct(product);

            dialogStage.showAndWait();

            if (controller.isSaveClicked()) {
                productsTable.refresh();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

