import java.util.Scanner;

public class Ex11_Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();

        long factorial = 1;
        for (int i = 2; i <= n; i++) factorial *= i;

        System.out.println(n + "! = " + factorial);
        sc.close();
    }
}
