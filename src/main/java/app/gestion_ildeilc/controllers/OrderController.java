package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Product;
import app.gestion_ildeilc.models.Line;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Arrays;

public class OrderController {

    // Orders sample data
    public static Order[] orders = {
            new Order("2", new Customer("1", "John", "Doe", "tets@gmail.com", "Lorem address"), "Order 2", 200.0, LocalDate.of(2021, 10, 2), Arrays.asList(new Line(2, new Product("2", "Product 2", "Lorem ipsum", 20.0))))
    };

    // Get all orders
    public static Order[] getAllOrders() {
        return orders;
    }

    // Get order by id
    public static Order getOrderById(String id) {
        for (Order order : orders) {
            if (Objects.equals(order.getId(), id)) {
                return order;
            }
        }
        return null;
    }

    // Delete order
    public static boolean deleteOrder(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (Objects.equals(orders[i].getId(), order.getId())) {
                Order[] newOrders = new Order[orders.length - 1];
                System.arraycopy(orders, 0, newOrders, 0, i);
                System.arraycopy(orders, i + 1, newOrders, i, orders.length - i - 1);
                orders = newOrders;
                return true;
            }
        }
        return false;
    }

}