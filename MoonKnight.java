class MoonKnight {
    String nama;
    int hp;
    int attackPower;

    MoonKnight(String nama, int hp, int attackPower) {
        this.nama = nama;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    void bonusFaseBulan(String faseBulan) {
        switch (faseBulan) {
            case "Purnama":
                hp += 20;
                System.out.println("Bonus Purnama: HP meningkat menjadi " + hp);
                break;
            case "Sabit":
                hp += 5;
                System.out.println("Bonus Sabit: HP meningkat menjadi " + hp);
                break;
            case "Setengah Lingkaran":
                hp += 10;
                System.out.println("Bonus Setengah Lingkaran: HP meningkat menjadi " + hp);
                break;
            default:
                System.out.println("Tidak ada bonus untuk fase bulan ini.");
                break;
        }
    }
}