import java.util.ArrayList;
import java.util.List;

class ECommerceSystem {
    private static final double SHIPPING_RATE = 10.0; // per kg - might change this later idk

    public static void checkout(Customer customer, Cart cart) {
        // validation - empty cart check
        if (cart.isEmpty()) {
            System.out.println("ERROR: Cart is empty!");
            return;
        }

        // check product availabilty and calculate subtotal
        double subtotal = 0;
        List<ShippableItem> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            // check if product is avilable - spelling mistake here lol
            if (!product.isAvailable()) {
                System.out.println("ERROR: Product " + product.getName() + " is out of stock or expired!");
                return;
            }

            // check if we have enought stock
            if (quantity > product.getQuantity()) {
                System.out.println("ERROR: Not enough stock for " + product.getName());
                return;
            }

            subtotal += item.getTotalPrice();

            // collect shippable items - this logic is bit messy but works
            if (product.needsShipping()) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add(new ShippableItem() {
                        @Override
                        public String getName() {
                            return product.getName();}

                        @Override
                        public double getWeight() {
                            if (product instanceof ExpirableProduct) {
                                return ((ExpirableProduct) product).getWeight();
                            } else if (product instanceof NonExpirableProduct) {
                                return ((NonExpirableProduct) product).getWeight();}
                            return 0;}});
                }
            }
        }

        // calculate shipping
        double shippingFee = 0;
        if (!shippableItems.isEmpty()) {
            double totalWeight = shippableItems.stream()
                    .mapToDouble(ShippableItem::getWeight)
                    .sum();
            shippingFee = totalWeight * SHIPPING_RATE;
        }

        double totalAmount = subtotal + shippingFee;

        // check customer balance - this is important!
        if (customer.getBalance() < totalAmount) {
            System.out.println("ERROR: Insufficient balance! Required: " + totalAmount + ", Available: " + customer.getBalance());
            return;
        }

        // process shipment first - order matters here
        if (!shippableItems.isEmpty()) {
            ShippingService.processShipment(shippableItems);
        }

        // print receipt  format to match
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        if (shippingFee > 0) {
            System.out.println("Shipping " + (int)shippingFee);
        }
        System.out.println("Amount " + (int)totalAmount);

        // remoce from customer balance and remove product quantites
        customer.reduceBalance(totalAmount);
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        System.out.println("Customer balance after payment: " + customer.getBalance());
        cart.clear(); // clear the ****** cart after checkout
    }
}