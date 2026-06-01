import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex27_LambdaExpressions {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Charlie", "Alice", "Eve", "Bob", "Diana");

        System.out.println("Before sort: " + names);
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("After sort : " + names);

        names.sort((a, b) -> b.compareTo(a));
        System.out.println("Reverse    : " + names);
    }
}
