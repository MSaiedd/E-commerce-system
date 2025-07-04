import java.util.ArrayList;
import java.util.List;
class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    //add products to cart
    public boolean add(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity");
            return false;}
        if (quantity > product.getQuantity()) {
            System.out.println("Not enough stock for " + product.getName());
            return false;
        }

        // check if product already in cart
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                // update quantity instead of adding new item
                int latest = item.getQuantity() + quantity;
                if (latest > product.getQuantity()) {
                    System.out.println("Total quantity exceeds stock");
                    return false;
                }
                items.remove(item);
                items.add(new CartItem(product, latest));
                return true;
            }
        }

        items.add(new CartItem(product, quantity));
        return true;
    }

    public List<CartItem> getItems() { return items; }



    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
