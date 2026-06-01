import java.util.Scanner;

public class Ex03_EvenOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();
        System.out.println(n + " is " + (n % 2 == 0 ? "Even" : "Odd"));
        sc.close();
    }
}
