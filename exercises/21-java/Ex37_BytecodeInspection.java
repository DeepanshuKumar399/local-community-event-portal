public class Ex37_BytecodeInspection {

    public int add(int a, int b) {
        return a + b;
    }

    public String greet(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        Ex37_BytecodeInspection obj = new Ex37_BytecodeInspection();
        System.out.println(obj.add(3, 4));
        System.out.println(obj.greet("World"));
        System.out.println("Now run: javap -c Ex37_BytecodeInspection");
    }
}
