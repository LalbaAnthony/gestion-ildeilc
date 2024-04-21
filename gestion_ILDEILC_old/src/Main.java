
// Importing utils
import java.time.LocalDate;

// Importing elements from the classes package
import classes.Order;

public class Main {
    public static void main(String[] args) {

        // Fill the array with 3 orders
        Order[] orders = new Order[3];
        orders[0] = new Order(1, 1, "Order 1", 100.0, LocalDate.of(2021, 10, 1));
        orders[1] = new Order(2, 2, "Order 2", 200.0, LocalDate.of(2021, 10, 2));

        // Display the orders
        for (Order order : orders) {
            order.printOrder();
        }   
    }
}