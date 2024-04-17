
// Importing utils
import java.time.LocalDate;

// Importing elements from the classes package
import classes.Order;

public class Main {
    public static void main(String[] args) {

        // Fill the array with 3 orders
        Order[] orders = new Order[3];
        orders[0] = new Order(1, 1, "Order 1", "Description 1", 100.0, "Pending", LocalDate.now().plusDays(5));
        orders[1] = new Order(2, 2, "Order 2", "Description 2", 200.0, "Delivered", LocalDate.now().plusDays(5));

        // Display the orders
        for (int i = 0; i < orders.length; i++) {
            orders[i].printOrder();
        }
    }
}