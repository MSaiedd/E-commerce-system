// base product class - probably should have used better names but whatever
abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;  // quantity available

    public Product(String name, double price, int qty) {
        this.name = name;
        this.price = price;
        this.quantity = qty;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity()
    { return quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    // check product is still Availableee?
    public abstract boolean isAvailable();
    public abstract boolean needsShipping();
}