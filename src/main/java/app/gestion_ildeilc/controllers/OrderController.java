package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Customer;
import java.time.LocalDate;
import java.util.Objects;

public class OrderController {

    // Orders sample data
    public static Order[] orders = {
            new Order("1", new Customer("1", "John", "Doe", "tets@gmail.com", "Lorem address"), "Order 1", 100.1, LocalDate.of(2021, 10, 1)),
            new Order("2", new Customer("1", "John", "Doe", "tets@gmail.com", "Lorem address"), "Order 2", 200.0, LocalDate.of(2021, 10, 2))
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

    // Create new order
    public static Order createOrder(Order order) {
        // Order[] newOrders = new Order[orders.length + 1];
        // System.arraycopy(orders, 0, newOrders, 0, orders.length);
        // newOrders[orders.length] = order;
        // orders = newOrders;
        // return order;

        return null;
    }

    // Update order
    public static Order updateOrder(Order order) {
        // for (int i = 0; i < orders.length; i++) {
        //     if (orders[i].getId() == order.getId()) {
        //         orders[i] = order;
        //         return order;
        //     }
        // }
        // return null;

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