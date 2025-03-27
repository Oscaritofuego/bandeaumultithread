package bandeau;

public class ExerciceAvecThreads {

    public static void main(String[] args) {
        ExerciceAvecThreads instance = new ExerciceAvecThreads();
        instance.exemple();
    }

    public void exemple() {

        Scenario s = makeScenario();
        // On cree les bandeaux
        Locked b1 = new Locked();
        Locked b2 = new Locked();
        Locked b3 = new Locked();
        System.out.println("CTRL-C pour terminer le programme");
        // On doit jouer le scénario en même temps sur les trois bandeaux
        Thread thread1 = new Thread(() -> s.playOn(b1));
        Thread thread2 = new Thread(() -> s.playOn(b2));
        Thread thread3 = new Thread(() -> s.playOn(b3));
        // On start les threads
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        s.playOn(b1);
    }

    private Scenario makeScenario() {
        // On crée un scenario
        Scenario s = new Scenario();
        // On lui ajoute des effets
        s.addEffect(new RandomEffect("Le jeu du pendu", 700), 1);
        s.addEffect(new TeleType("Je m'affiche caractère par caractère", 100), 1);
        s.addEffect(new Blink("Je clignote 10x", 100), 10);
        s.addEffect(new Zoom("Je zoome", 50), 1);
        s.addEffect(new FontEnumerator(10), 1);
        s.addEffect(new Rainbow("Comme c'est joli !", 30), 1);
        s.addEffect(new Rotate("2 tours à droite", 180, 4000, true), 2);
        s.addEffect(new Rotate("2 tours à gauche", 180, 4000, false), 2);
        return s;
    }
}