package app.gestion_ildeilc.views.pages;

import app.gestion_ildeilc.controllers.DeliveryNotesController;
import app.gestion_ildeilc.models.DeliveryNote;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeliveryNotesPageViewController {

    @FXML
    public TableView<DeliveryNote> deliveryNotesTable;

    public void initialize() {

        // ID column
        TableColumn<DeliveryNote, String> deliveryNoteIdCol = new TableColumn<>("ID");
        deliveryNoteIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Description column
        TableColumn<DeliveryNote, String> descriptionCol = new TableColumn<>("ID Order");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("idOrder"));

        // Delivery date column
        TableColumn<DeliveryNote, LocalDate> deliveryDateCol = new TableColumn<>("Date");
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        deliveryDateCol.setCellFactory(column -> new TextFieldTableCell<>(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? date.format(formatter) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return string != null && !string.isEmpty() ? LocalDate.parse(string, formatter) : null;
            }
        }));

        // Delete button column
        TableColumn<DeliveryNote, Void> deleteCol = new TableColumn<>(" ");
        deleteCol.setCellFactory(param -> new TableCell<DeliveryNote, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteButton.setOnAction(event -> {
                    DeliveryNote deliveryNote = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(deliveryNote);
                    DeliveryNotesController.deleteDeliveryNote(deliveryNote);
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
        deliveryNotesTable.getColumns().addAll(deliveryNoteIdCol, descriptionCol, deliveryDateCol, deleteCol);

        // Set autoresize property
        deliveryNotesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Populate table with sample data
        deliveryNotesTable.setItems(DeliveryNotesController.deliveryNotes);
    }
}

