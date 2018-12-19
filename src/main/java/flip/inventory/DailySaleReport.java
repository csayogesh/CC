package flip.inventory;

import java.util.TreeMap;

public interface DailySaleReport {
    TreeMap<Long, Double> getDailySaleAt();
}
