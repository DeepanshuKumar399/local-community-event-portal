public class Ex34_JavaModules {
    public static void main(String[] args) {
        System.out.println("Module layout:");
        System.out.println("  ex34-modules/");
        System.out.println("  ├── com.utils/");
        System.out.println("  │   ├── module-info.java  →  module com.utils { exports com.utils; }");
        System.out.println("  │   └── com/utils/StringUtils.java");
        System.out.println("  └── com.greetings/");
        System.out.println("      ├── module-info.java  →  module com.greetings { requires com.utils; }");
        System.out.println("      └── com/greetings/Main.java");
        System.out.println();
        System.out.println("Compile:");
        System.out.println("  javac -d mods/com.utils com.utils/module-info.java com.utils/com/utils/StringUtils.java");
        System.out.println("  javac --module-path mods -d mods/com.greetings com.greetings/module-info.java com.greetings/com/greetings/Main.java");
        System.out.println();
        System.out.println("Run:");
        System.out.println("  java --module-path mods -m com.greetings/com.greetings.Main");
    }
}
