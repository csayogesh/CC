package flip.inventory;

import java.util.HashSet;

public class CityRegion extends Region {
    public CityRegion(String bangalore) {
        super(bangalore, "City");
        allowedSubtypes = new HashSet<>();
    }
}
