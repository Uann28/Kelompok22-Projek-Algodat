class Kota {
    String nama;
    Villain headVillain;
    Kota next;
    String faseBulan;

    Kota(String nama, String faseBulan) {
        this.nama = nama;
        this.headVillain = null;
        this.next = null;
        this.faseBulan = faseBulan;
    }

    void tambahVillain(Villain villain) {
        if (headVillain == null) {
            headVillain = villain;
        } else {
            Villain current = headVillain;
            while (current.next != null) {
                current = current.next;
            }
            current.next = villain;
        }
    }
}