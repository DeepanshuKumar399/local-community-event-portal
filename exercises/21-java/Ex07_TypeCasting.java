public class Ex07_TypeCasting {
    public static void main(String[] args) {
        double d = 9.78;
        int i = (int) d;
        System.out.println("double " + d + " cast to int: " + i);

        int x = 42;
        double y = (double) x;
        System.out.println("int " + x + " cast to double: " + y);
    }
}
