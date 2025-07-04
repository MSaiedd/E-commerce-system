import java.util.List;

class ShippingService {
    public static void processShipment(List<ShippableItem> items) {
        if (items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (ShippableItem item : items) {
            double weight = item.getWeight();
            totalWeight += weight;
            // convert to grams
            System.out.println(item.getName() + " " + (int)(weight * 1000) + "g");
        }
        System.out.println("Total package weight " + totalWeight + "kg");
        System.out.println();
    }
}