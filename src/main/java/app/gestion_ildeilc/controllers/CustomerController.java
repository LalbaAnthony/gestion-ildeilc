package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Customer;
import java.time.LocalDate;
import java.util.Objects;

public class CustomerController {

    // Customers sample data
    public static Customer[] customers = {
            new Customer("1", "John", "Doe", "tets@gmail.com", "Lorem address"),
            new Customer("1", "John", "Doe", "tets@gmail.com", "Lorem address"),
    };

    // Get all customers
    public static Customer[] getAllCustomers() {
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
        for (int i = 0; i < customers.length; i++) {
            if (Objects.equals(customers[i].getId(), customer.getId())) {
                Customer[] newCustomers = new Customer[customers.length - 1];
                System.arraycopy(customers, 0, newCustomers, 0, i);
                System.arraycopy(customers, i + 1, newCustomers, i, customers.length - i - 1);
                customers = newCustomers;
                return true;
            }
        }
        return false;
    }

}