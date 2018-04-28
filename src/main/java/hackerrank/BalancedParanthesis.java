package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by yogesh.bh on 28/04/18.
 */
public class BalancedParanthesis {
    public static boolean isBalanced(String expression) {
        Map<Character, Character> popCh = new HashMap<Character, Character>() {{
            put('}', '{');
            put(')', '(');
            put(']', '[');
        }};
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (!popCh.containsKey(expression.charAt(i)))
                stack.push(expression.charAt(i));
            else if (!stack.empty() && stack.peek().equals(popCh.get(expression.charAt(i))))
                stack.pop();
            else return false;
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println((isBalanced(expression)) ? "YES" : "NO");
        }
    }
}
