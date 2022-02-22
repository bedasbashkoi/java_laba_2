public class Calculator {

    private final String[] tokens;
    private int pos = 0;

    public Calculator(String[] tokens) {
        this.tokens = tokens;
    }

    public Double parse() {
        Double result = expression();
        if (pos != tokens.length) {
            throw new IllegalStateException("Error");
        }
        return result;
    }

    private Double expression() {
        Double first = term();
        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }

            Double second = term();
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }

    private Double term() {

        Double first = factor();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                pos++;
            }

            Double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    private Double factor() {
        String next = tokens[pos];
        Double result;
        if (next.equals("(")) {
            pos++;
            result = expression();
            String closingBracket;
            if (pos < tokens.length) {
                closingBracket = tokens[pos];
            } else {
                throw new IllegalStateException("Unexpected end");
            }
            if (pos < tokens.length && closingBracket.equals(")")) {
                pos++;
                return result;
            }
            throw new IllegalStateException("')' expected" + closingBracket);
        }
        pos++;
        return Double.parseDouble(next);
    }
}
