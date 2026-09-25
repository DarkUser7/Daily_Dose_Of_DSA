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
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++; 
            Set<String> next = parseTerm();
            result.addAll(next);
        }
        return result;
    }
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
    private Set<String> parseFactor() {
        if (Character.isLetter(expression.charAt(index))) {
            Set<String> result = new HashSet<>();

            result.add(String.valueOf(expression.charAt(index)));
            index++;
            return result;
        }
        index++;
        Set<String> result = parseExpression();

        index++;

        return result;
    }
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