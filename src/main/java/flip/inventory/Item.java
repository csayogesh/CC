package flip.inventory;

public abstract class Item {
    long timestamp = System.currentTimeMillis() / 1000;
    String id;
    int quantity;

    public Item(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public final double getPrice(Store store) {
        return store.getItemPrice(id);
    }

    public String getId() {
        return id;
    }

    public final int getQuantity() {
        return quantity;
    }

    public final long getTimestamp() {
        return timestamp;
    }

    public void addQuantity(Item item) {
        quantity += item.quantity;
    }

    public void saleQuantities(int quantity) {
        this.quantity -= quantity;
    }
}
