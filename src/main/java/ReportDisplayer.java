import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ReportDisplayer {
    public static void main(String[] args) throws Exception {
        Region indiaRegion = new Country("India");
        Region karnatakaState = new StateRegion("Karnataka");
        Region cityBangalore = new CityRegion("Bangalore");
        indiaRegion.addRegion(karnatakaState);
        karnatakaState.addRegion(cityBangalore);
        Store store = new Store("store1");
        store.addItem(new FoodItem("Sandwitch", 10), 25);
        store.sale("Sandwitch", 2);
        store.sale("Sandwitch", 2);
        store.sale("Sandwitch", 2);

        Store store2 = new Store("store2");
        store2.addItem(new FoodItem("Sandwitch", 10), 1);
        store2.sale("Sandwitch", 2);
        store2.sale("Sandwitch", 2);

        cityBangalore.addStore(store);
        cityBangalore.addStore(store2);
        displayDailyReport(store.getDailySaleAt());
        displayDailyReport(karnatakaState.getDailySaleAt());
        displayDailyReport(indiaRegion.getDailySaleAt());
        System.out.println(store.getStockLeftAtGivenTime(System.currentTimeMillis() / 1000));
        System.out.println(indiaRegion.subRegionWithHighestTypeConsumption(Beverages.class));
        System.out.println(cityBangalore.subRegionWithHighestTypeConsumption(FoodItem.class));
        System.out.println(indiaRegion.storeWithHighestNumberOfUnitsSoldForAMonth());
    }

    private static void displayDailyReport(TreeMap<Long, Double> dailySaleAt) {
        System.out.println("New Daily report");
        for (Map.Entry<Long, Double> entry : dailySaleAt.entrySet()) {
            String formattedDate = new Date(entry.getKey() * 1000).toString();
            System.out.println(formattedDate + " : " + entry.getValue());
        }
        System.out.println("\n\n\n");
    }
}
