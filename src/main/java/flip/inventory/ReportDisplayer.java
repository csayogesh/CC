package flip.inventory;

import java.util.*;

public class ReportDisplayer {
    public static void main(String[] args) throws Exception {
        Region indiaRegion = new Country("India");
        Region karnatakaState = new StateRegion("Karnataka");
        Region cityBangalore = new CityRegion("Bangalore");
        Region maisurCity = new CityRegion("Maisur");
        indiaRegion.addRegion(karnatakaState);
        karnatakaState.addRegion(cityBangalore);
        karnatakaState.addRegion(maisurCity);
        Store store = new Store("store1");
        store.addItem(new FoodItem("Sandwitch", 20), 25);
        store.sale("Sandwitch", 2);
        store.sale("Sandwitch", 2);
        store.sale("Sandwitch", 12);

        Store store2 = new Store("store2");
        store2.addItem(new FoodItem("Sandwitch", 100), 1);
        store2.sale("Sandwitch", 2);
        store2.sale("Sandwitch", 52);


        Store store3 = new Store("store3");
        store3.addItem(new FoodItem("Sandwitch", 10), 2);
        store3.addItem(new Beverages("Pepsi", 10), 2);
        store3.addItem(new Beverages("Coke", 10), 2);
        store3.sale("Sandwitch", 2);
        store3.sale("Sandwitch", 4);

        maisurCity.addStore(store3);
        cityBangalore.addStore(store);
        cityBangalore.addStore(store2);

//        displayDailyReport(karnatakaState.getDailySaleAt());
//        displayDailyReport(store.getDailySaleAt());
//        displayDailyReport(karnatakaState.getDailySaleAt());
//        displayDailyReport(indiaRegion.getDailySaleAt());

//        List<flip.inventory.StockLeftReport> stores = new ArrayList<flip.inventory.StockLeftReport>() {{
//            add(indiaRegion);
//            add(karnatakaState);
//            add(cityBangalore);
//            add(store2);
//        }};
//        for (flip.inventory.StockLeftReport st : stores)
//            System.out.println(st.getStockLeftAtGivenTime(System.currentTimeMillis() / 1000));

//        System.out.println(store3.getStockLeftAtGivenTime(System.currentTimeMillis() / 1000));
//        System.out.println(indiaRegion.subRegionWithHighestTypeConsumption(flip.inventory.Beverages.class));
//        System.out.println(cityBangalore.subRegionWithHighestTypeConsumption(flip.inventory.FoodItem.class));
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
