package flip.inventory;

import java.util.HashSet;

public class Country extends Region {
    public Country(String india) {
        super(india, "flip.inventory.Country");
        allowedSubtypes = new HashSet<String>() {{
            add("State");
        }};
    }

    @Override
    public void addStore(Store store) throws Exception {
        throw new Exception("Cannot add store to flip.inventory.Country region");
    }
}
