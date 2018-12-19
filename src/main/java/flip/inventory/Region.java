package flip.inventory;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Region implements DailySaleReport, StockLeftReport, TypeWiseSaleReport, StoreWiseSalesReport {
    private final String type;
    private Map<String, Region> enclosedRegions = new HashMap<>();
    private String regionId;
    private Map<String, Store> stores = new HashMap<>();
    protected Set<String> allowedSubtypes;

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

    public String getId() {
        return regionId;
    }

    public List<Region> getListOfRegions() {
        return enclosedRegions.values().stream().collect(Collectors.toList());
    }

    public void addStore(Store store) throws Exception {
        stores.put(store.getId(), store);
    }

    public void addRegion(Region region) throws Exception {
        if (!allowedSubtypes.contains(region.getType()))
            throw new Exception("Not allowed to add " + region.getType() + " to the " + type + " region");
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

    public String subRegionWithHighestTypeConsumption(Class itemType) throws Exception {
        String state = null;
        double currentMax = 0;
        List<TypeWiseSaleReport> ls = new ArrayList<>();
        ls.addAll(getListOfRegions());
        ls.addAll(getAllStores());
        for (TypeWiseSaleReport reporter : ls) {
            Map<Class, Double> lastMonthReport = reporter.getTypeWiseSales();
            Double max = lastMonthReport.getOrDefault(itemType, 0.0);
            if (max >= currentMax) {
                currentMax = max;
                state = reporter.getId();
            }
        }
        return state;
    }

    public String storeWithHighestNumberOfUnitsSoldForAMonth() throws Exception {
        String state = null;
        double currentMax = 0;
        Map<String, Integer> lastMonthReport = getStoreWiseSales();
        for (Map.Entry<String, Integer> entry : lastMonthReport.entrySet())
            if (entry.getValue() >= currentMax) {
                currentMax = entry.getValue();
                state = entry.getKey();
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
        List<StoreWiseSalesReport> ls = new ArrayList<>();
        ls.addAll(getListOfRegions());
        ls.addAll(getAllStores());
        for (StoreWiseSalesReport region : ls) {
            Map<String, Integer> lastMonthReport = region.getStoreWiseSales();
            mergeToStoreSales(res, lastMonthReport);
        }
        return res;
    }

    private void mergeToStoreSales(Map<String, Integer> res, Map<String, Integer> lastMonthReport) {
        for (Map.Entry<String, Integer> entry : lastMonthReport.entrySet()) {
            res.putIfAbsent(entry.getKey(), 0);
            res.put(entry.getKey(), res.get(entry.getKey()) + entry.getValue());
        }
    }

    public int getStockLeftAtGivenTime(Long time) {
        List<StockLeftReport> ls = new ArrayList<>();
        ls.addAll(getAllStores());
        ls.addAll(getListOfRegions());
        int cnt = 0;
        for (StockLeftReport report : ls)
            cnt += report.getStockLeftAtGivenTime(time);
        return cnt;
    }
}
