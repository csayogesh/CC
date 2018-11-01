package hckrnk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Dates {

    public static void main(String[] args) {
        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Map<String, String> monthsMapping = new HashMap<>();
        int i = 1;
        for (String month : months) {
            monthsMapping.put(month, (i < 10) ? "0" + i : "" + i);
            i++;
        }
        String pattern = "([0-9]{1,2})(st|nd|th|rd) (" + Arrays.asList(months).stream().collect(Collectors.joining("|")) + ") ([0-9]{4})";
        Pattern p = Pattern.compile(pattern);
        String in = "6th Jun 1933";
        Matcher ma = p.matcher(in);
        ma.find();
        String ans = ma.group(4) + "-" + monthsMapping.get(ma.group(3)) + "-";
        int day = Integer.parseInt(ma.group(1));
        if (day < 10)
            ans += "0" + day;
        else ans += day;
        System.out.println(ans);
    }
}
