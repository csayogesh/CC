import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Region implements DailySaleReport {
    private final String type;
    private Map<String, Region> enclosedRegions = new HashMap<>();
    private String regionId;
    private Map<String, Store> stores = new HashMap<>();

    public Region(String regionId, String type) {
        this.regionId = regionId;
        this.type = type;
    }

    public String getRegionId() {
        return regionId;
    }

    public String getType() {
        return type;
    }

    public List<Region> getListOfRegions() {
        return enclosedRegions.values().stream().collect(Collectors.toList());
    }

    public void addStore(Store store) {
        stores.put(store.getId(), store);
    }

    public void addRegion(Region region) {
        enclosedRegions.put(region.getRegionId(), region);
    }

    public List<Store> getAllStores() {
        return stores.values().stream().collect(Collectors.toList());
    }

    @Override
    public TreeMap<Long, Double> getDailySaleAt() {
        TreeMap<Long, Double> finalResult = new TreeMap<>();
        for (Region region : getListOfRegions()) {
            TreeMap<Long, Double> dailySale = region.getDailySaleAt();
            mergeTwoSales(finalResult, dailySale);
        }
        for (Store store : getAllStores()) {
            TreeMap<Long, Double> dailySale = store.getDailySaleAt();
            mergeTwoSales(finalResult, dailySale);
        }
        return finalResult;
    }

    private void mergeTwoSales(TreeMap<Long, Double> finalResult, TreeMap<Long, Double> dailySale) {
        for (Map.Entry<Long, Double> entry : dailySale.entrySet()) {
            finalResult.putIfAbsent(entry.getKey(), 0.0);
            finalResult.put(entry.getKey(), finalResult.get(entry.getKey()) + entry.getValue());
        }
    }

    public String stateWithHighestBeverageConsumption() throws Exception {
        if (!type.equals("Country"))
            throw new Exception("Not valid report on type " + type);
        String state = null;
        double currentMax = 0;
        for (Region region : getListOfRegions()) {
            Map<Class, Double> lastMonthReport = region.getTypeWiseSales();
            Double max = lastMonthReport.getOrDefault(Beverages.class, 0.0);
            if (max >= currentMax) {
                currentMax = max;
                state = region.getRegionId();
            }
        }
        return state;
    }

    public String storeWithHighestNumberOfUnitsSoldForAMonth() throws Exception {
        if (!type.equals("Country"))
            throw new Exception("Not valid report on type " + type);
        String state = null;
        double currentMax = 0;
        for (Region region : getListOfRegions()) {
            Map<String, Integer> lastMonthReport = region.getStoreWiseSales();
            for (Map.Entry<String, Integer> entry : lastMonthReport.entrySet())
                if (entry.getValue() >= currentMax) {
                    currentMax = entry.getValue();
                    state = entry.getKey();
                }
        }
        return state;
    }

    public Map<Class, Double> getTypeWiseSales() {
        Map<Class, Double> res = new HashMap<>();
        for (Region region : getListOfRegions()) {
            Map<Class, Double> lastMonthReport = region.getTypeWiseSales();
            mergeToFinal(res, lastMonthReport);
        }
        for (Store store : getAllStores()) {
            Map<Class, Double> dailySale = store.getTypeWiseSales();
            mergeToFinal(res, dailySale);
        }
        return res;
    }

    private void mergeToFinal(Map<Class, Double> finalRepo, Map<Class, Double> lastMonthReport) {
        for (Map.Entry<Class, Double> entry : lastMonthReport.entrySet()) {
            finalRepo.putIfAbsent(entry.getKey(), 0d);
            finalRepo.put(entry.getKey(), finalRepo.get(entry.getKey()) + entry.getValue());
        }
    }

    public Map<String, Integer> getStoreWiseSales() {
        Map<String, Integer> res = new HashMap<>();
        for (Region region : getListOfRegions()) {
            Map<String, Integer> lastMonthReport = region.getStoreWiseSales();
            mergeToStoreSales(res, lastMonthReport);
        }
        for (Store store : getAllStores()) {
            int dailySale = store.getStoreWiseSales();
            res.put(store.getId(), dailySale);
        }
        return res;
    }

    private void mergeToStoreSales(Map<String, Integer> res, Map<String, Integer> lastMonthReport) {
        for (Map.Entry<String, Integer> entry : lastMonthReport.entrySet()) {
            res.putIfAbsent(entry.getKey(), 0);
            res.put(entry.getKey(), res.get(entry.getKey()) + entry.getValue());
        }
    }
}
