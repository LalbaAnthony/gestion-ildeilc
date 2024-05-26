package app.gestion_ildeilc.views.dialogs;

import app.gestion_ildeilc.controllers.CustomersController;
import app.gestion_ildeilc.controllers.InvoicesController;
import app.gestion_ildeilc.controllers.ProductsController;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import app.gestion_ildeilc.models.Invoice;
import app.gestion_ildeilc.models.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class InvoiceDialogViewController {

    @FXML
    public Label pageTitle;

    @FXML
    private TextField idField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<String> paidComboBox;

    @FXML
    private DatePicker deliveredDatePicker;

    @FXML
    private Spinner<Double> totalSpinner;

    @FXML
    private TableView<Line> linesTable;

    private Stage dialogStage;
    private Invoice invoice;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        // Set values paid select
        paidComboBox.getItems().addAll("Pending", "Paid");

        // Initialize the Spinner for total
        SpinnerValueFactory<Double> totalValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 0.1);
        totalSpinner.setValueFactory(totalValueFactory);

        // StringConverter configuration to display client's names
        customerComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Customer customer) {
                return customer != null ? customer.getNiceName() : "";
            }

            @Override
            public Customer fromString(String string) {
                return customerComboBox.getItems().stream().filter(customer -> customer.getNiceName().equals(string)).findFirst().orElse(null);
            }
        });
        customerComboBox.setItems(FXCollections.observableArrayList(CustomersController.customers)); // Populate the customer select

        // Product column
        TableColumn<Line, String> linesProductCol = new TableColumn<>("Product");
        linesProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        linesProductCol.setCellValueFactory(cellData -> {
            Product product = cellData.getValue().getProduct();
            return new SimpleStringProperty(product.getName());
        });

        // Quantity column
        TableColumn<Line, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Price column
        TableColumn<Line, String> priceCol = new TableColumn<>("Unit price");
        priceCol.setCellValueFactory(cellData -> {
            Product product = cellData.getValue().getProduct();
            String priceString = String.format("%.2f €", product.getPrice());
            return new SimpleStringProperty(priceString);
        });

        // Add all columns to the table
        linesTable.getColumns().addAll(linesProductCol, quantityCol, priceCol);

        // Set autoresize property
        linesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setInvoice(Invoice invoice) {

        this.invoice = invoice;

        // Set all field with the invoice data
        idField.setText(String.valueOf(invoice.getId()));
        descriptionField.setText(invoice.getDescription());
        paidComboBox.setValue(invoice.getPaid());
        customerComboBox.setValue(invoice.getCustomer());
        deliveredDatePicker.setValue(invoice.getDeliveredDate());
        totalSpinner.getValueFactory().setValue(invoice.getTotal());
        linesTable.setItems(FXCollections.observableArrayList(invoice.getLines()));
    }

    @FXML
    private void handleSave() {
        // Set of all values, might be replaced in the future by a dedicated methode
        // invoice.setId(idField.getText()); // No set cuz it cannot be changed by the user
        invoice.setDescription(descriptionField.getText());
        invoice.setPaid(paidComboBox.getValue());
        invoice.setCustomer(customerComboBox.getValue());
        invoice.setDeliveredDate(deliveredDatePicker.getValue());
        // invoice.setTotal(totalSpinner.getValue()); // No set cuz it cannot be changed by the user
        invoice.setLines(linesTable.getItems());

        invoice.calculateTotal();  // Calculate the total before saving

        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
