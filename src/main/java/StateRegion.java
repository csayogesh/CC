import java.util.HashSet;

public class StateRegion extends Region {
    public StateRegion(String karnataka) {
        super(karnataka, "State");
        allowedSubtypes = new HashSet<String>() {{
            add("City");
        }};
    }

    @Override
    public void addStore(Store store) throws Exception {
        throw new Exception("Cannot add store to the state region");
    }
}
