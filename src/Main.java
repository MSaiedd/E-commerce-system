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
        Customer customer = new Customer("John Doe", 1000);

        // create cart and add items
        Cart cart = new Cart();

        System.out.println("=== Test Case 1: Normal checkout ===");
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        ECommerceSystem.checkout(customer, cart);

        System.out.println("\n=== Test Case 2: Empty cart ===");
        ECommerceSystem.checkout(customer, cart);

        System.out.println("\n=== Test Case 3: Insufficient balance ===");
        cart.add(tv, 2);  // this will cost 100k
        ECommerceSystem.checkout(customer, cart);

        System.out.println("\n=== Test Case 4: Out of stock ===");
        cart.clear();
        cart.add(cheese, 20);  // more than available
        ECommerceSystem.checkout(customer, cart);

        System.out.println("\n=== Test Case 5: Expired product ===");
        ExpirableProduct expiredCheese = new ExpirableProduct("Expired Cheese", 100, 5, "2025-01-01", true, 0.2);
        cart.clear();
        cart.add(expiredCheese, 1);
        ECommerceSystem.checkout(customer, cart);

        System.out.println("\n=== Test Case 6: Mixed products ===");
        cart.clear();
        cart.add(cheese, 1);
        cart.add(tv, 1);
        // this should fail due to insufficient balance
        ECommerceSystem.checkout(customer, cart);

        System.out.println("\nRemaining customer balance: " + customer.getBalance());

    }
}
