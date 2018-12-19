package flip.inventory;

public class Sale {
    private Item item;
    private int quantity;
    private double unitPrice;
    private long saleTime = System.currentTimeMillis()/1000;

    public Sale(Item item, int quantity, double unitPrice) {
        this.item = item;
        item.saleQuantities(quantity);
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public long getSaleTime() {
        return saleTime;
    }

}
