import java.util.HashSet;

public class Country extends Region {
    public Country(String india) {
        super(india, "Country");
        allowedSubtypes = new HashSet<String>() {{
            add("State");
        }};
    }

    @Override
    public void addStore(Store store) throws Exception {
        throw new Exception("Cannot add store to Country region");
    }
}
