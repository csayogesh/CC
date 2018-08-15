package leet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseWords {
    public String reverseWords(String s) {
        List<String> ls = Arrays.asList(s.split("\\s+"));
        Collections.reverse(ls);
        return ls.stream().filter(a->a.length()>0).collect(Collectors.joining(" "));
    }
}
