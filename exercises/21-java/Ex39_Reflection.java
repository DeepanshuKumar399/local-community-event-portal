import java.lang.reflect.Method;

public class Ex39_Reflection {

    static class Calculator {
        public int    add(int a, int b)      { return a + b; }
        public int    multiply(int a, int b) { return a * b; }
        private String secret()             { return "Hidden method!"; }
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("Ex39_Reflection$Calculator");

        System.out.println("Class: " + clazz.getName());
        System.out.println("\nDeclared Methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println("  " + m.getName() + " | params: " + m.getParameterCount());
        }

        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method addMethod = clazz.getMethod("add", int.class, int.class);
        int result = (int) addMethod.invoke(instance, 10, 20);
        System.out.println("\nDynamic invoke add(10, 20) = " + result);

        Method secret = clazz.getDeclaredMethod("secret");
        secret.setAccessible(true);
        System.out.println("Private method result: " + secret.invoke(instance));
    }
}
