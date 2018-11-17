package hckrnk.meetings;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PersonInventory {
    private static Map<String, Person> people = new HashMap<>();

    public static void init() throws Exception {
        try {
            Scanner sc = new Scanner(new File("persons.csv"));
            while (sc.hasNext()) {
                String[] ar = sc.nextLine().split(",");
                String name = ar[0].toLowerCase().trim();
                String post = ar[1].toLowerCase().trim();
                Person person = new Person();
                person.setName(name);
                person.setDesignation(post);
                people.put(name, person);
            }
        } catch (Exception e) {
            throw new Exception("Error populating people inventory", e);
        }
    }

    public static Person getPersonDetails(String name) {
        if (people.containsKey(name))
            return people.get(name);
        return null;
    }
}
