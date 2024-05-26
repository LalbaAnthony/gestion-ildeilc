package app.gestion_ildeilc.controllers;

import app.gestion_ildeilc.models.Customer;
import app.gestion_ildeilc.models.Line;
import app.gestion_ildeilc.models.Order;
import app.gestion_ildeilc.models.Product;  
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrdersController {

    // Orders sample data
    public static Order[] orders = {
            new Order("2", new Customer("1", "John", "Doe", "tets@gmail.com", "Lorem address"), "Order 2", 40.0, LocalDate.of(2021, 10, 2), Arrays.asList(new Line(2, new Product("2", "Product 2", "Lorem ipsum", 20.0))))
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

    // Generate a unique ID
    public static String generateId() {
        int maxId = 0;
        for (Order order : orders) {
            int id = Integer.parseInt(order.getId());
            if (id > maxId) {
                maxId = id;
            }
        }
        return String.valueOf(maxId + 1);
    }


    // Add a new order
    public static void addOrder(Order newOrder) {
        // Add the new order to the orders array
        List<Order> ordersList = new ArrayList<>(Arrays.asList(orders));
        ordersList.add(newOrder);
        orders = ordersList.toArray(new Order[0]);
    }

}