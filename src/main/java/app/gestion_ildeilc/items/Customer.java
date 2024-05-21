package app.gestion_ildeilc.items;

import java.time.LocalDate;

public class Customer {

    private final int id;
    private String name;
    private String email;
    private String address;
    private final LocalDate creationDate;

    public Customer(int id, String name, String email, String address)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.creationDate = LocalDate.now();
    }

    // ================ Getters ================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    // ================ Setters ================

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // ================ Methods ================

    public void printCustomer() {
        System.out.println("===================================");
        System.out.println("Customer ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + getAddress());
        System.out.println("Creation Date: " + getCreationDate());
        System.out.println("===================================");
    }
}

