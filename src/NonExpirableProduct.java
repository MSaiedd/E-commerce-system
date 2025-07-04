class NonExpirableProduct extends Product {
    private boolean shippable;
    private double weight;

    public NonExpirableProduct(String name, double price, int qty, boolean shippable, double weight) {
        super(name, price, qty);
        this.shippable = shippable;
        this.weight = weight;
    }
    @Override
    public boolean isAvailable() {
        return quantity > 0;  // just check stock
    }
    @Override
    public boolean needsShipping() {
        return shippable;
    }
    public double getWeight() { return weight; }
}