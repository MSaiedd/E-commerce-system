import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // create some products
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100, 10, "2025-08-01", true, 0.2);

        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150, 5, "2025-07-15", true, 0.7);

        NonExpirableProduct tv = new NonExpirableProduct("TV", 50000, 3, true, 15.0);


        NonExpirableProduct scratchCard = new NonExpirableProduct("Mobile scratch card", 50, 100, false, 0.001);

        // create customer
        Customer customer = new Customer("saied", 1000);
        // create cart and add items
        Cart cart = new Cart();


        ExpirableProduct expiredCheese = new ExpirableProduct("Expired Cheese", 100, 5, "2025-01-01", true, 0.2);
        cart.add(expiredCheese, 1);
        ECommerceSystem.checkout(customer, cart);
        System.out.println("\nRemaining customer balance: " + customer.getBalance());
    }
}
