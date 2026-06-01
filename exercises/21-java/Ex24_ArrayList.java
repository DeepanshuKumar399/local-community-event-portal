import java.util.ArrayList;
import java.util.Scanner;

public class Ex24_ArrayList {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("How many students? ");
        int count = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter name " + (i + 1) + ": ");
            names.add(sc.nextLine());
        }

        System.out.println("\nStudent List:");
        for (String name : names) System.out.println("  - " + name);
        sc.close();
    }
}
