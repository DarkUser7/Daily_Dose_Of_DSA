import java.util.*;

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // Handles union: e1,e2,e3
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation: ab{c,d}ef
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != ','
                && expression.charAt(index) != '}') {

            Set<String> next = parseFactor();

            result = concatenate(result, next);
        }

        return result;
    }

    // Handles a single letter or {...}
    private Set<String> parseFactor() {

        // Case 1: lowercase letter
        if (Character.isLetter(expression.charAt(index))) {
            Set<String> result = new HashSet<>();

            result.add(String.valueOf(expression.charAt(index)));
            index++;

            return result;
        }

        // Case 2: {...}
        index++; // skip '{'

        Set<String> result = parseExpression();

        index++; // skip '}'

        return result;
    }

    // Cartesian-product concatenation
    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}