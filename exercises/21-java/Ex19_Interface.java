public class Ex19_Interface {

    interface Playable {
        void play();
    }

    static class Guitar implements Playable {
        public void play() { System.out.println("Guitar: Strumming chords 🎸"); }
    }

    static class Piano implements Playable {
        public void play() { System.out.println("Piano: Playing keys 🎹"); }
    }

    public static void main(String[] args) {
        Playable guitar = new Guitar();
        Playable piano  = new Piano();

        guitar.play();
        piano.play();
    }
}
