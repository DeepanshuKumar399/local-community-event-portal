public class Ex18_Inheritance {

    static class Animal {
        void makeSound() {
            System.out.println("Animal makes a sound.");
        }
    }

    static class Dog extends Animal {
        @Override
        void makeSound() {
            System.out.println("Dog says: Bark!");
        }
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog    = new Dog();

        animal.makeSound();
        dog.makeSound();
    }
}
