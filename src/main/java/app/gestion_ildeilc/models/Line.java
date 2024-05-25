package app.gestion_ildeilc.models;

public class Line {
    private int quantity;
    private Product product;

    public Line(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    // ================ Getters ================

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    // ================ Setters ================

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
