package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.DeliveryNote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Objects;

public class DeliveryNotesController {

    // DeliveryNotes sample data
    public static ObservableList<DeliveryNote> deliveryNotes = FXCollections.observableArrayList();

    static {
        // Sample invoice 1
        DeliveryNote deliveryNote1 = new DeliveryNote("1", "1", LocalDate.of(2024, 5, 30));

        // Adding invoices to the list
        deliveryNotes.addAll(deliveryNote1);
    }

    // Get all deliveryNotes
    public static ObservableList<DeliveryNote> getAllDeliveryNotes() {
        return deliveryNotes;
    }

    // Get deliveryNote by id
    public static DeliveryNote getDeliveryNoteById(String id) {
        for (DeliveryNote deliveryNote : deliveryNotes) {
            if (Objects.equals(deliveryNote.getId(), id)) {
                return deliveryNote;
            }
        }
        return null;
    }

    // Delete deliveryNote
    public static boolean deleteDeliveryNote(DeliveryNote deliveryNote) {
        return deliveryNotes.remove(deliveryNote);
    }

    // Generate a unique ID
    public static String generateId() {
        int maxId = 0;
        for (DeliveryNote deliveryNote : deliveryNotes) {
            int id = Integer.parseInt(deliveryNote.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.valueOf(maxId + 1);
    }


    // Add a new deliveryNote
    public static void addDeliveryNote(DeliveryNote newDeliveryNote) {
        deliveryNotes.add(newDeliveryNote);
    }
}