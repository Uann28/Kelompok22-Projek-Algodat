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

        Traversal traversal = new Traversal(moonKnight);

        traversal.tambahKota(newYork);
        traversal.tambahKota(gotham);
        traversal.tambahKota(cairo);
        traversal.tambahKota(paris);
        traversal.tambahKota(tokyo);

        traversal.tambahEdge(newYork, gotham, 5);
        traversal.tambahEdge(gotham, cairo, 10);
        traversal.tambahEdge(gotham, paris, 25);
        traversal.tambahEdge(cairo, tokyo, 20);
        traversal.tambahEdge(cairo, paris, 15);
        traversal.tambahEdge(paris, tokyo, 5);

        traversal.searchAndDisplayVillain();
        System.out.println();
        
        traversal.displayAllCities();   
        System.out.println();
        
        traversal.startTraversal();
    }
}
