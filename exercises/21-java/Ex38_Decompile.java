public class Ex38_Decompile {

    private final String message;

    public Ex38_Decompile(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) {
        Ex38_Decompile obj = new Ex38_Decompile("Decompile me!");
        obj.printMessage();
        System.out.println("Compile this file, then open the .class file in JD-GUI or run CFR.");
    }
}
