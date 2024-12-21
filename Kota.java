class Kota { 
    String nama;
    Villain headVillain;
    String faseBulan;
    Tetangga headTetangga;
    Kota next;

    Kota(String nama, String faseBulan) {
        this.nama = nama;
        this.headVillain = null;
        this.faseBulan = faseBulan;
        this.headTetangga = headTetangga;
        this.next = null;
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

    void tambahTetangga(Kota tetangga, int bobot) {
        Tetangga newTetangga = new Tetangga(tetangga, bobot);
        if (headTetangga == null) {
            headTetangga = newTetangga;
        } else {
            Tetangga current = headTetangga;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTetangga;
        }
    }
} 