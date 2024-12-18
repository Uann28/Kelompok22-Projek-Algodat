public class Main {
    public static void main(String[] args) {
        MoonKnight moonKnight = new MoonKnight("Moon Knight", 100, 50);

        Kota newYork = new Kota("New York", "Purnama");
        Kota gotham = new Kota("Gotham", "Sabit");
        Kota cairo = new Kota("Cairo", "Setengah Lingkaran");
        Kota paris = new Kota("Paris", "Purnama");
        Kota tokyo = new Kota("Tokyo", "Sabit");

        newYork.tambahVillain(new Villain("Green Goblin", 60, 20));
        newYork.tambahVillain(new Villain("Scarlet Witch", 30, 15));
        gotham.tambahVillain(new Villain("Joker", 30, 15));
        cairo.tambahVillain(new Villain("Amon Ra", 65, 25));
        paris.tambahVillain(new Villain("Magneto", 30, 25));
        tokyo.tambahVillain(new Villain("Doctor Doom", 105, 35));

        newYork.next = gotham;
        gotham.next = cairo;
        cairo.next = paris;
        paris.next = tokyo;

        Traversal traversal = new Traversal(moonKnight, newYork);

        String targetVillain = "Joker";
        Villain foundVillain = traversal.linearSearchVillain(targetVillain);
        if (foundVillain != null) {
            System.out.println(targetVillain + " ditemukan dengan kekuatan sebesar: " + foundVillain.kekuatan);
        } else {
            System.out.println("Villain " + targetVillain + " tidak ditemukan.");
        }
        System.out.println();

        traversal.displayAllCities();
        System.out.println();

        traversal.startTraversal();
    }
}
