
public class Main {
    public static void main(String[] args) {
        String[] tokens = {"2", "+", "3", "*", "(", "4", "-", "5", ")", "+", "6", "-", "7"};
        Calculator value = new Calculator(tokens);
        value.parse();
        System.out.println(value);
    }
}