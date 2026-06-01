public class Ex08_OperatorPrecedence {
    public static void main(String[] args) {
        int r1 = 10 + 5 * 2;
        System.out.println("10 + 5 * 2 = " + r1 + "  (* evaluated first)");

        int r2 = (10 + 5) * 2;
        System.out.println("(10 + 5) * 2 = " + r2 + "  (parentheses first)");

        int r3 = 20 / 4 + 3 * 2 - 1;
        System.out.println("20 / 4 + 3 * 2 - 1 = " + r3 + "  (/ and * before + and -)");

        int r4 = 10 % 3 + 1;
        System.out.println("10 % 3 + 1 = " + r4 + "  (% before +)");
    }
}
