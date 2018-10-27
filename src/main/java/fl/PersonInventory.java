package fl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PersonInventory {
    private static Map<String, Person> people = new HashMap<>();

    public static void init() throws FileNotFoundException {
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
    }

    public static Person getPersonDetails(String name) {
        if (people.containsKey(name))
            return people.get(name);
        return null;
    }
}
