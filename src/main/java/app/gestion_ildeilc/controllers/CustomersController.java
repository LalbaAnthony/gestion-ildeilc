package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class CustomersController {

    // Customers sample data
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    static {
        Customer customer1 = new Customer("1", "Henri", "Car", "eauetanis@gmail.com", "Lorem address");
        Customer customer2 = new Customer("1", "Jean", "Bombeur", "beureetjambon@gmail.com", "Lorem address");

        // Adding customers to the list
        customers.addAll(customer1, customer2);
    }

    // Get all customers
    public static ObservableList<Customer> getAllCustomers() {
        return customers;
    }

    // Get customer by id
    public static Customer getCustomerById(String id) {
        for (Customer customer : customers) {
            if (Objects.equals(customer.getId(), id)) {
                return customer;
            }
        }
        return null;
    }

    // Delete customer
    public static boolean deleteCustomer(Customer customer) {
        return customers.remove(customer);
    }

    // Generate a unique ID
    public static String generateId() {
        int maxId = 0;
        for (Customer customer : customers) {
            int id = Integer.parseInt(customer.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.valueOf(maxId + 1);
    }


    // Add a new customer
    public static void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }
}