import java.util.Scanner;

public class Ex21_CustomException {

    static class InvalidAgeException extends Exception {
        InvalidAgeException(String message) { super(message); }
    }

    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Age " + age + " is invalid. Must be 18 or older.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        try {
            checkAge(age);
            System.out.println("Access granted. Welcome!");
        } catch (InvalidAgeException e) {
            System.out.println("Access denied: " + e.getMessage());
        }
        sc.close();
    }
}
