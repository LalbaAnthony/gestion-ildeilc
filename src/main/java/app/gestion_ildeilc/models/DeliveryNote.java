package app.gestion_ildeilc.models;

import java.time.LocalDate;

public class DeliveryNote {

    private final String id;
    private final String idOrder;
    private final LocalDate date;

    public DeliveryNote(String id, String idOrder, LocalDate date) {
        this.id = id;
        this.idOrder = idOrder;
        this.date = date;
    }

    // ================ Getters ================

    public String getId() {
        return id;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public LocalDate getDate() {
        return date;
    }
    // ================ Setters ================

    // No setter cuz this item is not meant to be modified

    // ================ Computed ================
}
