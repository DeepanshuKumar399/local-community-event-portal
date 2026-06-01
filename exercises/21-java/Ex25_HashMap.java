import java.util.HashMap;
import java.util.Scanner;

public class Ex25_HashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("How many entries? ");
        int count = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter student ID  : ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            students.put(id, name);
        }

        System.out.print("\nEnter ID to search: ");
        int searchId = sc.nextInt();
        String found = students.get(searchId);
        System.out.println(found != null ? "Found: " + found : "No student with ID " + searchId);
        sc.close();
    }
}
