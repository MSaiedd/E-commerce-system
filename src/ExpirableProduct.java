import java.time.LocalDate;

// products that can expire - cheese, biscuits etc
class ExpirableProduct extends Product {
    private LocalDate expiryDate;
    private boolean shippable;
    private double weight;  // in kg

    public ExpirableProduct(String name, double price, int qty, String expiry, boolean shippable, double weight) {
        super(name, price, qty);
        this.expiryDate = LocalDate.parse(expiry);
        this.shippable = shippable;
        this.weight = weight;
    }

    @Override
    public boolean isAvailable() {
        // shit, is it expired?
        return quantity > 0 && !expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean needsShipping() {
        return shippable;
    }

    public double getWeight() { return weight; }
}