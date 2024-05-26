package app.gestion_ildeilc.views.pages;

import app.gestion_ildeilc.controllers.InvoicesController;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Invoice;
import app.gestion_ildeilc.views.dialogs.InvoiceDialogViewController;
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

public class InvoicesPageViewController {

    @FXML
    public TableView<Invoice> invoicesTable;

    public void initialize() {

        // ID column
        TableColumn<Invoice, String> invoiceIdCol = new TableColumn<>("ID");
        invoiceIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Description column
        TableColumn<Invoice, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Paid column
        TableColumn<Invoice, LocalDate> paidCol = new TableColumn<>("Paid ?");
        paidCol.setCellValueFactory(new PropertyValueFactory<>("paid"));

        // Customer name column
        TableColumn<Invoice, String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(cellData -> {
            Customer customer = cellData.getValue().getCustomer();
            return new SimpleStringProperty(customer.getNiceName());
        });

        // Delivery date column
        TableColumn<Invoice, LocalDate> deliveredDateCol = new TableColumn<>("Delivery date");
        deliveredDateCol.setCellValueFactory(new PropertyValueFactory<>("deliveredDate"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        deliveredDateCol.setCellFactory(column -> new TextFieldTableCell<>(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? date.format(formatter) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return string != null && !string.isEmpty() ? LocalDate.parse(string, formatter) : null;
            }
        }));

        // Total column
        TableColumn<Invoice, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totalCol.setCellFactory(column -> new TableCell<Invoice, Double>() {
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

        // Modify button column
        TableColumn<Invoice, Void> modifyCol = new TableColumn<>(" ");
        modifyCol.setCellFactory(param -> new TableCell<Invoice, Void>() {
            private final Button modifyButton = new Button("Modify");

            {
                modifyButton.setStyle("-fx-background-color: #485ea8; -fx-text-fill: white; -fx-font-weight: bold;");
                modifyButton.setOnAction(event -> {
                    Invoice invoice = getTableView().getItems().get(getIndex());
                    showInvoiceDialogModify(invoice);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Invoice invoice = getTableView().getItems().get(getIndex());
                    // Disable the modification if depending on the status
                    modifyButton.setDisable(!invoice.canModify());
                    HBox hBox = new HBox(modifyButton);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        // Delete button column
        TableColumn<Invoice, Void> deleteCol = new TableColumn<>(" ");
        deleteCol.setCellFactory(param -> new TableCell<Invoice, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    Invoice invoice = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(invoice);
                    InvoicesController.deleteInvoice(invoice);
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
        invoicesTable.getColumns().addAll(invoiceIdCol, descriptionCol, paidCol, customerNameCol, deliveredDateCol, totalCol, modifyCol, deleteCol);

        // Set autoresize property
        invoicesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Populate table with sample data
        invoicesTable.setItems(InvoicesController.invoices);
    }

    private void showInvoiceDialogModify(Invoice invoice) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getResource("/app/gestion_ildeilc/invoice-dialog-view.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found!");
                return;
            }
            loader.setLocation(fxmlLocation);
            Parent page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Invoice");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(invoicesTable.getScene().getWindow());
            Scene scene = new Scene(page, 480, 640);
            dialogStage.setScene(scene);

            InvoiceDialogViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // If is an invoice modification
            controller.pageTitle.setText("Modify an invoice");
            controller.setInvoice(invoice);

            dialogStage.showAndWait();

            if (controller.isSaveClicked()) {
                invoicesTable.refresh();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

