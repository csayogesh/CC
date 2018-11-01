package hckrnk;

import java.util.*;
import java.util.stream.Collectors;

public class Shopkeeper {
    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>();
        TreeMap<Integer, AbstractMap.SimpleEntry<Integer, List<Integer>>> map = new TreeMap<>();
        long finalPrices = 0;
        int i = 0;
        for (int price : prices) {
            Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, List<Integer>>> en = map.ceilingEntry(price);
            while (en != null) {
                finalPrices += (en.getKey() - price) * en.getValue().getKey();
                map.remove(en.getKey());
                en = map.ceilingEntry(price);
            }
            map.putIfAbsent(price, new AbstractMap.SimpleEntry<Integer, List<Integer>>(0, new ArrayList<Integer>()));
            AbstractMap.SimpleEntry<Integer, List<Integer>> s = map.get(price);
            s.getValue().add(i++);
            map.put(price, new AbstractMap.SimpleEntry<Integer, List<Integer>>(s.getKey() + 1, s.getValue()));
        }
        List<Integer> indices = new ArrayList<>();
        for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, List<Integer>>> entry : map.entrySet()) {
            finalPrices += entry.getKey() * entry.getValue().getKey();
            indices.addAll(entry.getValue().getValue());
        }
        System.out.println(finalPrices);
        System.out.println(indices.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }
}
