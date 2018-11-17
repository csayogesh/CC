import java.util.*;

public class Store implements DailySaleReport, StockLeftReport, TypeWiseSaleReport {
    private String id;
    private List<Sale> sales = new ArrayList<>();
    private List<Item> addedItems = new ArrayList<>();
    private Map<Long, Integer> rawItemsCount = new HashMap<>();
    private Map<String, Double> menuCard = new HashMap<>();

    public Store(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getItemPrice(String id) {
        return menuCard.get(id);
    }

    public void sale(String id, int quantity) throws Exception {
        Item item = findItem(id);
        if (item == null)
            throw new Exception("Item " + id + " not present at store");
        if (item.getQuantity() < quantity)
            throw new Exception("Insufficient quantity is available at the store");
        Sale sale = new Sale(item, quantity, menuCard.get(id));
        sales.add(sale);
    }

    private Item findItem(String id) {
        for (Item item : addedItems)
            if (item.getId().equals(id))
                return item;
        return null;
    }

    public void addItem(Item item, double unitPrice) {
        menuCard.put(item.getId(), unitPrice);
        rawItemsCount.putIfAbsent(item.getTimestamp(), 0);
        rawItemsCount.put(item.getTimestamp(), rawItemsCount.get(item.getTimestamp()) + item.getQuantity());
        for (Item item1 : addedItems)
            if (item1.getId().equals(item.getId())) {
                item1.addQuantity(item);
                return;
            }
        addedItems.add(item);
    }

    public TreeMap<Long, Double> getDailySaleAt() {
        TreeMap<Long, Double> report = new TreeMap<>();
        for (Sale sale : sales) {
            long dayId = sale.getSaleTime() - sale.getSaleTime() % Constants.ONEDAY;
            report.putIfAbsent(dayId, 0.0);
            report.put(dayId, report.get(dayId) + sale.getQuantity() * sale.getUnitPrice());
        }
        return report;
    }

    public int getStockLeftAtGivenTime(Long time) {
        int soldQuantity = 0;
        int addedQuantity = 0;
        for (Sale sale : sales)
            if (sale.getSaleTime() <= time)
                soldQuantity += sale.getQuantity();
        for (Map.Entry<Long, Integer> ent : rawItemsCount.entrySet())
            if (ent.getKey() <= time)
                addedQuantity += ent.getValue();
        return addedQuantity - soldQuantity;
    }

    public Map<Class, Double> getTypeWiseSales() {
        Map<Class, TreeMap<Long, Double>> res = new HashMap();
        for (Sale sale : sales) {
            long monthId = sale.getSaleTime() - sale.getSaleTime() % Constants.ONEMONTH;
            res.putIfAbsent(sale.getClass(), new TreeMap<>());
            res.get(sale.getClass()).putIfAbsent(monthId, 0.0);
            res.get(sale.getClass()).putIfAbsent(monthId, res.get(sale.getClass()).get(monthId) + sale.getUnitPrice() * sale.getQuantity());
        }

        Map<Class, Double> finalRes = new HashMap<>();
        for (Map.Entry<Class, TreeMap<Long, Double>> entry : res.entrySet()) {
            finalRes.put(entry.getKey(), entry.getValue().lastEntry().getValue());
        }
        return finalRes;
    }

    public int getStoreWiseSales() {
        TreeMap<Long, Integer> res = new TreeMap<>();
        for (Sale sale : sales) {
            long monthId = sale.getSaleTime() - sale.getSaleTime() % Constants.ONEMONTH;
            res.putIfAbsent(monthId, 0);
            res.put(monthId, res.get(monthId) + sale.getQuantity());
        }
        return res.lastEntry().getValue();
    }
}
