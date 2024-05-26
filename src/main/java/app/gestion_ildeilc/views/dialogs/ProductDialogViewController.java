package app.gestion_ildeilc.views.dialogs;

import app.gestion_ildeilc.controllers.ProductsController;
import app.gestion_ildeilc.models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductDialogViewController {

    @FXML
    public Label pageTitle;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Spinner<Double> priceSpinner;

    @FXML
    private Spinner<Integer> stockSpinner;

    public boolean isModification = true;

    private Stage dialogStage;
    private Product product;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        // Initialize the Spinner for price
        SpinnerValueFactory<Double> totalValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 0.1);
        priceSpinner.setValueFactory(totalValueFactory);

        // Initialize the Spinner for stock
        SpinnerValueFactory<Integer> stockValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0, 1);
        stockSpinner.setValueFactory(stockValueFactory);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {

        this.product = product;

        // Set all field with the product data
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        descriptionField.setText(product.getDescription());
        priceSpinner.getValueFactory().setValue(product.getPrice());
        stockSpinner.getValueFactory().setValue(product.getStock());
    }

    @FXML
    private void handleSave() {
        // Set of all values, might be replaced in the future by a dedicated methode
        // product.setId(idField.getText()); // No set cuz it cannot be changed by the user
        product.setName(nameField.getText());
        product.setDescription(descriptionField.getText());
        product.setPrice(priceSpinner.getValue());
        product.setStock(stockSpinner.getValue());

        if (!this.isModification) {
            ProductsController.addProduct(product);
        }

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
