package fl;

import java.util.HashMap;
import java.util.Map;

public class OrganisationStrategies {
    private static Map<String, Integer> rankings = new HashMap<String, Integer>() {{
        put("ceo", 1);
        put("coo", 2);
        put("director", 3);
        put("manager", 4);
    }};

    public static int getRanking(String designation) {
        if (rankings.containsKey(designation))
            return rankings.get(designation);
        return Integer.MAX_VALUE;
    }

    public static int getRankingForPerson(String name) {
        Person person = PersonInventory.getPersonDetails(name);
        if (person != null)
            return getRanking(person.getDesignation());
        return Integer.MAX_VALUE;
    }
}
